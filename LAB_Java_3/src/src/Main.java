package src;

import TaxiFleet.DirectorFleet;
import TaxiFleet.Fleet;
import enums.typeAuto;
import taxi.Automobile;
import taxi.BusTaxi;
import taxi.Passenger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static Logger LOGGER = LOGGER = Logger.getLogger(Main.class.getName());
    static {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("D:\\University\\Labs\\JAVA\\LAB_Java_3\\src\\log\\logManager.log");
            LOGGER = Logger.getLogger(Main.class.getName());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Logger getLOGGER(){
        return LOGGER;
    }
    public static void main(String[] args) {
        try {
            DirectorFleet directorFleet = new DirectorFleet();
            //NODE create FleetTaxi

            Fleet<Automobile> fleet1 = new Fleet<>();
            //NODE exception to LOGGER divided by zero
            //int x = 0/0;

            LOGGER.log(Level.INFO, "Создание объекта passengerTaxi1");

            //NODE create object passengerTaxi1
            Passenger passengerTaxi1 = new Passenger(9700,180, 60, 40, 6.5, typeAuto.Universal);
            passengerTaxi1.FuelToDistance();
            passengerTaxi1.ConditionerSwitch();
            passengerTaxi1.ConditionerSwitch();
            passengerTaxi1.GetTypeTaxi();
            passengerTaxi1.GoToOrder("Немига 14");

            LOGGER.log(Level.INFO, "Создание объекта passengerTaxi2");

            //NODE create object passengerTaxi2
            Passenger passengerTaxi2 = new Passenger(10000,150, 50, 18, 6.3, typeAuto.Sedan);
            passengerTaxi2.FuelToDistance();
            passengerTaxi2.ConditionerSwitch();
            passengerTaxi2.ConditionerSwitch();
            passengerTaxi2.GetTypeTaxi();

            LOGGER.log(Level.INFO, "Создание объекта busTaxi1");

            //NODE create object busTaxi1
            BusTaxi busTaxi1 = new BusTaxi(7090, 80, 80, 30, 8, typeAuto.Bus, 3.0f);
            passengerTaxi1.ConditionerSwitch();
            busTaxi1.FuelToDistance();
            busTaxi1.GetTypeTaxi();

            LOGGER.log(Level.INFO, "Создание объекта busTaxi2");

            //NODE create object busTaxi2
            BusTaxi busTaxi2 = new BusTaxi(10200, 100, 75, 43, 9, typeAuto.Bus, 4.2f);
            passengerTaxi2.ConditionerSwitch();
            busTaxi2.FuelToDistance();
            busTaxi2.GetTypeTaxi();

            LOGGER.log(Level.INFO, "Создание объекта fleet1");

            //NODE add FleeTaxi

            fleet1.add(busTaxi1);
            fleet1.add(busTaxi2);
            fleet1.add(passengerTaxi1);
            fleet1.add(passengerTaxi2);

            System.out.printf("Количество в коллекции: %d\n",fleet1.size());

            System.out.printf("Стоиомость таксопарка: %d\n",directorFleet.AllPriceFleet(fleet1));

            // NODE sort fleet taxi first
            ArrayList<Automobile> automobiles = fleet1.getList();
            System.out.println("List до сортировки ->");
            System.out.printf("%s %s\n","Тип авто","Расход топлива");
            for (Automobile automobile : automobiles) {
                System.out.printf("%s  ",automobile.getType());
                System.out.printf("%s\n",automobile.getFuelConsumption());
            }
            Collections.sort(fleet1.getList());

            // NODE sort fleet taxi first
            automobiles = fleet1.getList();
            System.out.println("List после сортировки ->");
            System.out.printf("%.20s %.20s","Тип авто","Расход топлива\n");
            for (Automobile automobile : automobiles) {
                System.out.printf("%s  ",automobile.getType());
                System.out.printf("%s\n",automobile.getFuelConsumption());
            }

            //directorFleet.Serialize(fleet1);
            Fleet<Automobile> fleet2 = directorFleet.Deserialize("D:\\University\\Labs\\JAVA\\LAB_Java_3\\src\\xml\\serializeTaxi.xml");
            System.out.printf("Количество элементов из десериализации: %d ",fleet2.size());

        }
        catch (Exception e){
            LOGGER.log(Level.WARNING,e.toString());
        }
    }
}
