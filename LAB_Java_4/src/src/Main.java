package src;
import sax.SAXHandler;
import TaxiFleet.DirectorFleet;
import TaxiFleet.Fleet;
import enums.typeAuto;
import taxi.Automobile;
import taxi.BusTaxi;
import taxi.Passenger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    static Logger LOGGER  = Logger.getLogger(Main.class.getName());
    static final String fileXML = "src\\xml\\serializeTaxi.xml";
    static final String fileLOG = "src\\log\\logManager.log";
    static {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler(fileLOG);
            LOGGER = Logger.getLogger(Main.class.getName());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void WriteInfoLOGGER(String message){
        LOGGER.log(Level.INFO, message);
    }

    public static void main(String[] args) {
        try {
            //NODE create directorFleet
            DirectorFleet directorFleet = new DirectorFleet();

            //NODE create FleetTaxi
            Fleet<Automobile> fleet1 = new Fleet<>();

            WriteInfoLOGGER("Создание объекта passengerTaxi1");

            //NODE create object passengerTaxi1 ("ford",9700,180, 60,40, 6.5, typeAuto.Universal);
            Passenger passengerTaxi1 = new Passenger("ford", 140, 60,6.5, 40, 9800,typeAuto.Universal,true);
            passengerTaxi1.FuelToDistance();
            passengerTaxi1.ConditionerSwitch();
            passengerTaxi1.ConditionerSwitch();
            passengerTaxi1.GetTypeTaxi();

            WriteInfoLOGGER( "Создание объекта passengerTaxi2");

            //NODE create object passengerTaxi2
            Passenger passengerTaxi2 = new Passenger("renault", 150,70, 7d, 18, 10200, typeAuto.Sedan,true);
            passengerTaxi2.FuelToDistance();
            passengerTaxi2.ConditionerSwitch();
            passengerTaxi2.ConditionerSwitch();
            passengerTaxi2.GetTypeTaxi();

            WriteInfoLOGGER( "Создание объекта busTaxi1");

            //NODE create object busTaxi1
            BusTaxi busTaxi1 = new BusTaxi("skoda",120, 80, 6.4d, 30, 18000, typeAuto.Bus, 3.0f);
            busTaxi1.FuelToDistance();
            busTaxi1.GetTypeTaxi();

            WriteInfoLOGGER("Создание объекта busTaxi2");

            //NODE create object busTaxi2
            BusTaxi busTaxi2 = new BusTaxi("mercedes",100, 80, 7.6d, 43, 19000, typeAuto.Universal, 4.2f);
            busTaxi2.FuelToDistance();
            busTaxi2.GetTypeTaxi();

            WriteInfoLOGGER( "Создание объекта busTaxi3");

            //NODE create object busTaxi2
            BusTaxi busTaxi3 = new BusTaxi("peugeout",140, 120, 7.5, 41, 17000, typeAuto.Hatchback, 4.2f);
            busTaxi2.FuelToDistance();
            busTaxi2.GetTypeTaxi();

            WriteInfoLOGGER( "Создание объекта fleet1");

            //NODE add FleeTaxi

            fleet1.add(busTaxi1);
            fleet1.add(busTaxi2);
            fleet1.add(busTaxi3);
            fleet1.add(passengerTaxi1);
            fleet1.add(passengerTaxi2);

            System.out.printf("Количество в коллекции: %d\n",fleet1.size());

            System.out.printf("Стоиомсть таксопарка: %d\n",directorFleet.AllPriceFleet(fleet1));

            // NODE sort fleet taxi first
            ArrayList<Automobile> automobiles = fleet1.getList();
            System.out.println("List до сортировки ->");
            System.out.printf("%1$s %2$s","Тип авто","Расход топлива");
            for (Automobile automobile : automobiles) {
                System.out.println(automobile.getFuelConsumption());
            }
            Collections.sort(fleet1.getList());

            // NODE sort fleet taxi first
            automobiles = fleet1.getList();
            System.out.println("List после сортировки ->");
            System.out.printf("%1$s %2$s","Тип авто","Расход топлива");
            for (Automobile automobile : automobiles) {
                System.out.println(automobile.getFuelConsumption());
            }

            //directorFleet.SerializeXML(fleet1);
            Fleet<Automobile> fleet2 = directorFleet.deserializeXML(fileXML);
            System.out.printf("Количество элементов из десериализации: %d \n",fleet2.size());
            directorFleet.validateXML();

            // NODE parsing src\xml\serializeTaxi.xml
            SAXParserFactory parserFactor = SAXParserFactory.newInstance();
            SAXParser parser = parserFactor.newSAXParser();
            SAXHandler handler = new SAXHandler();
            parser.parse(new File(fileXML),handler);

            handler.sort();
            handler.show();

            // NODE serialize to JSON
            directorFleet.serializeJSON(fleet1);
            ArrayList<Automobile> arrayList = directorFleet.deserializeJSON("src\\json\\taxi.json");
            WriteInfoLOGGER("Количество объектов сериализации JSON: "+arrayList.size());

            // NODE using Stream API
            WriteInfoLOGGER("using Stream API");
            fleet1.searchUseStream("ford");
            fleet1.geaAvgFuelConsumption();
            List<String> result = fleet1.sortUseStreamName();
            System.out.print("Отсортированный список марок такси: \n");
            for (String str:result
                 ) {
                System.out.printf("%s | ",str);
            }
        }
        catch (Exception e){
            WriteInfoLOGGER(e.toString());
        }
    }
}
