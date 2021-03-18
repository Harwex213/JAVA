package taxi;
import enums.typeAuto;

public class Automobile implements Comparable<Automobile> {

    private String name;                            // название авто
    private Integer maxSpeed;                       // максимальная скорость
    private Integer tankSize;                       // размер топливного бака
    private Double fuelConsumption;                 // расход топлива
    private Integer currentFuelCapacity;            // текущее количество топлива
    private Integer price;                          // стоимость
    private typeAuto type;                          // тип автомобиля

    public Automobile() {
        super();
    }

    // region gets/sets
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Automobile(String name, Integer maxSpeed, Integer tankSize, Double fuelConsumption, Integer currentFuelCapacity, Integer price, typeAuto type) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.tankSize = tankSize;
        this.fuelConsumption = fuelConsumption;
        this.currentFuelCapacity = currentFuelCapacity;
        this.price = price;
        this.type = type;
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
        return this.name.compareTo(o.name);
    }
}
