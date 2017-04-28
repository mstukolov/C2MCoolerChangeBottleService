package app.bluemix.subscriber;

/**
 * Created by Maxim on 21.04.2017.
 */
import org.eclipse.paho.client.mqttv3.*;

public class C2MSmartCoolerSubscriber {

    final static String orgId = "kwxqcy";
    final static String clientId ="a:kwxqcy:app1";
    final static String domain =".messaging.internetofthings.ibmcloud.com:1883";
    final String authmethod = "a-kwxqcy-1dw7hvzvwk";
    final String authtoken = "tsM8N(FS@iOc3CId+5";
    public static final String BROKER_URL = "tcp://" + orgId + domain;
    String topic = "iot-2/type/SmartCooler/id/C2MSmartCooler2/evt/+/fmt/json";
    boolean isSSL = false;



    private MqttClient mqttClient;

    public C2MSmartCoolerSubscriber() {

        try {
            mqttClient = new MqttClient(BROKER_URL, clientId);

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(authmethod);
        options.setPassword(authtoken.toCharArray());

        try {

            mqttClient.setCallback(new SubscribeCallback());
            mqttClient.connect(options);
            mqttClient.subscribe(topic);

            System.out.println("Subscriber is now listening to " + topic);

        } catch (MqttException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] args) {

        final C2MSmartCoolerSubscriber subscriber = new C2MSmartCoolerSubscriber();
        subscriber.start();
    }
}
