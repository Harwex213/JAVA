package Cars;

public class CarRight extends Car {
    public CarRight(){
        QuantityCarRight++;
    }

    public void run() {
        try {
            road.acquire();
            sleep(500);

            road.release();                                                // освобождаем семофор --
            sleep(100);

            Switcher();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
