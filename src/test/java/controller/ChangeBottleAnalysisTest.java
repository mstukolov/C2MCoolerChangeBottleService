package controller;

import org.junit.Before;
import org.junit.Test;

import javax.print.DocFlavor;

import static org.junit.Assert.*;

/**
 * Created by Maxim on 27.04.2017.
 */
public class ChangeBottleAnalysisTest {
    ChangeBottleAnalysis changeBottleAnalysis;
    @Before
    public void initTest() {
        changeBottleAnalysis = new ChangeBottleAnalysis();
    }

    @Test
    public void eventDeviceDefinition() throws Exception {
        String deviceid = "SmartCoolerTest";
        Double newValue = 3.8;
        Double previousValue = 4D;
        Double vmax = 19.0;

        System.out.println("Max=" + vmax*1.05);
        System.out.println("Min=" + vmax*0.95);
        System.out.println("Delta=" + (newValue - previousValue));

        changeBottleAnalysis.eventDeviceDefinition(deviceid, newValue, previousValue, vmax);

    }
    @Test
    public void periodicDataTransfer() throws Exception {
        String deviceid = "SmartCoolerTest";
        changeBottleAnalysis.eventDeviceDefinition(deviceid, 19.3, 0.2, 19.0);
        changeBottleAnalysis.eventDeviceDefinition(deviceid, 19.3, 0.2, 19.0);
        changeBottleAnalysis.eventDeviceDefinition(deviceid, 19.3, 0.2, 19.0);
        changeBottleAnalysis.eventDeviceDefinition(deviceid, 19.3, 0.2, 19.0);
        changeBottleAnalysis.eventDeviceDefinition(deviceid, 19.3, 0.2, 19.0);
        changeBottleAnalysis.eventDeviceDefinition(deviceid, 19.3, 0.2, 19.0);

    }


}