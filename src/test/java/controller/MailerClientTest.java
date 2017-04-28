package controller;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Maxim on 27.04.2017.
 */
public class MailerClientTest {
    MailerClient mailerClient;
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before CalculatorTest.class");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("After CalculatorTest.class");
    }
    @Before
    public void initTest() {
        mailerClient = new MailerClient();
    }
    @After
    public void afterTest() {
        mailerClient = null;
    }
    @Ignore
    @Test(timeout = 2000)
    public void sender2000() throws Exception {
        mailerClient.sender();
    }
    @Ignore
    @Test(timeout = 5000)
    public void sender5000() throws Exception {
        mailerClient.sender();
    }
    @Ignore
    @Test(expected =  java.lang.RuntimeException.class)
    public void testConnection(){
        mailerClient.sender();
    }
    //@Test(expected =  java.lang.RuntimeException.class)
    @Test(timeout = 5000)
    public void sendCurrentMessage(){
        mailerClient.sender("Hello, maxim Stukolov!!!");
    }

}