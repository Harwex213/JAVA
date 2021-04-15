package TaxiFleet;

import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import src.Main;
import taxi.Automobile;
import taxi.BusTaxi;
import taxi.Passenger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@DisplayName("Test class DirectorFleet")
public class DirectorFleet extends Assert implements Comparator<Automobile> {

    private static final Object objectNull = null;
    private static final Object objectNotNull = new Object();
    private static String result = "";
    private static String test = "";
    private Map<String, String> data = new HashMap<String, String>();;

    @BeforeAll
    static void  initAll()
    {
        Object object = new Object();
        Assertions.assertNotNull(object, "Object in method beforeClassInit = null");
        System.out.println("Начало теста");
    }

    @BeforeEach
    public  void init(){
        System.out.println("------ @BeforeEach ------");

    }

    @Test
    @Ignore
    @DisplayName("Test method Serialize ignored")
    public void Serialize() throws JAXBException, FileNotFoundException {
        FileOutputStream file = new FileOutputStream("D:\\University\\Labs\\JAVA\\LAB_Java_3\\src\\xml\\serializeTaxi.xml");
        Logger LOGGER = Main.getLOGGER();
        JAXBContext context = JAXBContext.newInstance(Fleet.class, Automobile.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //marshaller.marshal(list, file); // сериализация
    }

    @Test
    public int Deserialize() throws JAXBException, FileNotFoundException{
        test = "Тест метода Deserialize";
        FileInputStream file = new FileInputStream("D:/University/Labs/JAVA/LAB_Java_7/src/xml/serializeTaxi.xml");
        JAXBContext context = JAXBContext.newInstance(Fleet.class, Passenger.class, BusTaxi.class);
        Unmarshaller marshaller = context.createUnmarshaller();
        Object object = marshaller.unmarshal(file);
        Fleet<Automobile> fleet = new Fleet<>();
        fleet = (Fleet<Automobile>)object;
        result = String.valueOf(fleet.size());

        Assertions.assertNotEquals(0, fleet.size(), "Количество объектов равно нулю");
        return fleet.size();
    }

    @ParameterizedTest
    @CsvSource({"3,7","2,8"})
    public int sum(int a,int b) throws InterruptedException {
        test = "Тест метода sum";
        Assertions.assertEquals(10,a+b,"a + b != 10");
        return a+b;
    }

    @Test(timeOut = 1000)
    public void TimeOutTest() throws InterruptedException {
        test = "TimeOutTest";
        Thread.sleep(1000);
        result = "seccuses";
    }

    @DataProvider
    public Object[][] concurrencyData() {
        return new Object[][] {
                {"1", "2"},
                {"3", "4"},
                {"5", "6"},
                {"7", "8"},
                {"9", "10"},
                {"11", "12"},
                {"13", "14"},
                {"15", "16"},
                {"17", "18"},
                {"19", "20"},
        };
    }

    @Test(dataProvider = "concurrencyData",priority = 1,singleThreaded = true, invocationCount = 1, invocationTimeOut = 40)
    public void testParallelData(String first, String second) {
        final Thread thread = Thread.currentThread();
        System.out.printf("#%d %s: %s : %s", thread.getId(), thread.getName(), first, second);
        System.out.println();
    }

    @Override
    public int compare(Automobile o1, Automobile o2) {
        Double a = o1.getFuelConsumption();
        Double b = o2.getFuelConsumption();
        return a.compareTo(b);
    }

    @AfterEach
    void tearDown() {
        System.out.println(result);
        System.out.println("------  успешно ------");
        System.out.println("------  @AfterEach ------");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("@AfterAll");
        //Assertions.fail("Stop test");
    }

    @Test(groups = { "groupTestA"})
    static void runTestA1(){
        System.out.println("runTestA1");
    }

    @Test(groups = "groupTestA")
    static void runTestA2(){
        System.out.println("runTestA2");
    }
    @Test(groups = "groupTestA")
    static void runTestA3(){
        System.out.println("runTestA1");
    }

}
