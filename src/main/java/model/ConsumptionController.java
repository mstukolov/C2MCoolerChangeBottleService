package model;

import java.util.*;

/**
 * Created by Maxim on 28.04.2017.
 */
public class ConsumptionController {
    private static ConsumptionController _instance = null;
    private Set<BottleConsumptionBean> data;

    public ConsumptionController() {
        data = new HashSet<>();
    }

    public static synchronized ConsumptionController getInstance() {
        if (_instance == null)
            _instance = new ConsumptionController();
        return _instance;
    }
    public void increment(String deviceid){
        BottleConsumptionBean bean = findDeviceElement(deviceid);
        if(bean == null){
            BottleConsumptionBean newDevice = new BottleConsumptionBean(deviceid, 1);
            data.add(newDevice);
        }else{
            bean.setQty(bean.getQty() + 1);
        }
    }

    public BottleConsumptionBean findDeviceElement(String deviceid){
        Optional<BottleConsumptionBean> existDevice = data.stream().filter(a -> a.getDeviceid() == deviceid).findFirst();

        if(existDevice.isPresent()){
            return existDevice.get();
        } else {
            return null;
        }
    }

    public Set<BottleConsumptionBean> getData() {
        return data;
    }
}
