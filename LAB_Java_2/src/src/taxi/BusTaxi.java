package src.taxi;

import src.enums.typeAuto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "bus")
public class BusTaxi extends Automobile{
    private float valueBody;

//region get/set
    @XmlElement(name = "valueBody")
    public float getValueBody() {
        return valueBody;
    }

    public void setValueBody(float valueBody) {
        this.valueBody = valueBody;
    }
//endregion

    public BusTaxi(int price, int maxSpeed, int tankSize, int currentFuelCapacity, double fuelConsumption, typeAuto typeauto, float valueBody) {
        super(price, maxSpeed, tankSize, currentFuelCapacity, fuelConsumption, typeauto);
        this.valueBody = valueBody;
    }

    public BusTaxi() {
    }
}