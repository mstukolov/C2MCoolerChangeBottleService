package controller;

import java.util.ArrayDeque;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LinkedTreeMap;
import model.ConsumptionController;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by Maxim on 21.04.2017.
 */
public class ChangeBottleAnalysis {

    static MailerClient mailerClient = new MailerClient();
    static ConsumptionController controller = new ConsumptionController();
    static Map<String, ArrayDeque> sensorData = new HashMap<String, ArrayDeque>();
    static Double maxDelta = 1.5;



    public static void AnalyseNewMessageData(String topic, String message){

        System.out.println("Message arrived to Analysis. Topic: " + topic + "  Message: " + message.toString());
        Map<String, String> _raw = ((Map<String, String>)(new Gson().fromJson(message, Map.class)).get("d"));

        String deviceid = _raw.get("deviceid");
        Double newValue = Double.parseDouble(_raw.get("param1"));
        Double vMax = Double.parseDouble(_raw.get("param2"));

        Double previousValue = 0.0;
        try{
            previousValue = getDeviceLastValue(deviceid);
        }catch (Exception ex){
            System.out.println("Previous value is null");
        }


        if(sensorData.containsKey(deviceid)){
            sensorData.get(deviceid).add(newValue);
        }else{
            ArrayDeque<Double> stack = new ArrayDeque<Double>();
            stack.add(newValue);
            sensorData.put(deviceid, stack);
        }
        eventDeviceDefinition(deviceid, newValue, previousValue, vMax);
    }

    public static void eventDeviceDefinition(String deviceid, Double _newValue, Double _previousValue, Double _vmax){

        Double delta = _newValue - _previousValue;

        if(  delta > 0){
            System.out.println("Объем воды увеличился");
            if( delta  >= _vmax * 0.95 && delta <= _vmax * 1.05){
                controller.increment(deviceid);
                System.out.println("Произошла смена бутылки. Использовано: " + controller.findDeviceElement(deviceid).getQty());

                //mailerClient.sender("Произошла смена бутылки");
            }
            if( delta  < _vmax * 0.98 || delta  >= _vmax * 1.02){
                System.out.println("Кто-то надавил на кулер. Warning!!!");
                //mailerClient.sender("Кто-то надавил на кулер. Warning!!!");
            }
        }else if( (-1)*maxDelta < delta && delta <= 0){
            System.out.println("Кто-то отлил водички");
            //mailerClient.sender("Кто-то отлил водички");
        }
    }

    public static Double getDeviceLastValue(String deviceid){
        if(sensorData.get(deviceid).peekLast() != null){
            return (Double) sensorData.get(deviceid).peekLast();
        }else {
            return 0.0;
        }
    }
}
