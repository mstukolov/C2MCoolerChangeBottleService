package app.bluemix.subscriber;

import controller.ChangeBottleAnalysis;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by Maxim on 21.04.2017.
 */
public class SubscribeCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable cause) {
        //This is called when the connection is lost. We could reconnect here.
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        /*System.out.println("Message arrived. Topic: " + topic + "  Message: " + message.toString());*/
        ChangeBottleAnalysis.AnalyseNewMessageData(topic, message.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        //no-op
    }
}
