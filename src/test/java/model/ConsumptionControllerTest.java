package model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by Maxim on 28.04.2017.
 */
public class ConsumptionControllerTest {
    ConsumptionController controller;

    @Before
    public void initTest() {
        controller = new ConsumptionController();
    }

    @Test
    public void testIncrement() throws Exception {
        controller.increment("dev1");
        controller.increment("dev1");
        controller.increment("dev1");
        controller.increment("dev2");
        controller.increment("dev2");
    }

    @Test
    public void dataIsNotEmpty() throws Exception {
        assertFalse(!controller.getData().isEmpty());
    }
    @Test
    public void dataIsNotNull() throws Exception {
        assertTrue(controller.getData() != null);
    }


}