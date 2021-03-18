package taxi;

import enums.typeAuto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BusTaxi")
public class BusTaxi extends Automobile{

    public BusTaxi(String name, Integer maxSpeed, Integer tankSize, Double fuelConsumption, Integer currentFuelCapacity, Integer price, typeAuto type, float valueBody) {
        super(name, maxSpeed, tankSize, fuelConsumption, currentFuelCapacity, price, type);
    }

    public BusTaxi() {
    }
}