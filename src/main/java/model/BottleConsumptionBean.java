package model;

import java.util.Date;

/**
 * Created by MAKS on 28.04.2017.
 */
public class BottleConsumptionBean {

    String deviceid;
    Integer qty;
    Date lastDate;

    public BottleConsumptionBean(String deviceid, Integer qty) {
        this.deviceid = deviceid;
        this.qty = qty;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
