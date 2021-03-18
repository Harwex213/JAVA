package taxi;

import enums.typeAuto;
import lombok.Data;


public abstract class Automobile implements Comparable<Automobile> {

    private Integer maxSpeed;                       // максимальная скорость
    private Integer tankSize;                       // размер топливного бака
    private Double fuelConsumption;                 // расход топлива
    private Integer currentFuelCapacity;            // текущее количество топлива
    private Integer price;                          // стоимость
    private typeAuto type;                          // тип автомобиля

    public Automobile() {}

    // region gets/sets
    public int getPrice() {return price;}

    public void setPrice(int price) {
        this.price = price;
    }
    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public int getTankSize() {
        return tankSize;
    }

    public void setTankSize(int tankSize) {
        this.tankSize = tankSize;
    }
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
    public typeAuto getType() {
        return type;
    }

    public void setType(typeAuto type) {
        this.type = type;
    }
    public int getCurrentFuelCapacity() {
        return currentFuelCapacity;
    }

    public void setCurrentFuelCapacity(int currentFuelCapacity) {
        this.currentFuelCapacity = currentFuelCapacity;
    }

    //endregion;

    public Automobile(int price, int maxSpeed, int tankSize,int currentFuelCapacity, double fuelConsumption, typeAuto typeauto) {
        this.price = price;
        this.maxSpeed = maxSpeed;
        this.tankSize = tankSize;
        this.fuelConsumption = fuelConsumption;
        this.currentFuelCapacity = currentFuelCapacity;
        this.type = typeauto;
    }

    public void FuelToDistance(){
        System.out.printf("Осталось топлива на %f км.",currentFuelCapacity*fuelConsumption);
        System.out.println();
    }

    public void GetTypeTaxi(){
        switch (this.type){
            case Coupe: System.out.println("Тип такси купе"); break;
            case Sedan: System.out.println("Тип такси седан"); break;
            case Hatchback: System.out.println("Тип такси хэтчбек"); break;
            case Universal: System.out.println("Тип такси универсал"); break;
            case Bus: System.out.println("Тип такси микроавтобус"); break;
        }
    }

    @Override
    public int compareTo(Automobile o) {
        return this.fuelConsumption.compareTo(o.fuelConsumption);
    }
}
