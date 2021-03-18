package taxi;

import enums.typeAuto;
import interfaces.TaxiAdditionalOptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Passenger")

public class Passenger extends Automobile implements TaxiAdditionalOptions
{


    private static class Engine{

    }
    private boolean conditioner;

    @Override
    public void ConditionerSwitch() {
        if(this.conditioner){
            System.out.println("Кондиционер выключен");
            this.conditioner = false;
        }
        else {
            System.out.println("Кондиционер включен");
            this.conditioner = true;
        }
    }

    @Override
    public void GoToOrder(String address) {
        System.out.printf("Такси выехало по адрессу: %s \n",address);
    }
    public Passenger(String name, Integer maxSpeed, Integer tankSize, Double fuelConsumption, Integer currentFuelCapacity, Integer price, typeAuto type, boolean conditioner) {
        super(name, maxSpeed, tankSize, fuelConsumption, currentFuelCapacity, price, type);
        this.conditioner = conditioner;
    }

    public Passenger() {
    }
}
