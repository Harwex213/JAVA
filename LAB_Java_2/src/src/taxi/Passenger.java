package src.taxi;

import src.enums.typeAuto;
import src.interfaces.TaxiAdditionalOptions;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passenger")
public class Passenger extends Automobile implements TaxiAdditionalOptions
{
    private static class Engine{

    }
    private boolean conditioner;

    public Passenger(int price, int maxSpeed, int tankSize, int currentFuelCapacity, double fuelConsumption, typeAuto typeauto) {
        super(price,maxSpeed, tankSize, currentFuelCapacity, fuelConsumption, typeauto);
    }

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

    public Passenger() {
        super();

    }
}
