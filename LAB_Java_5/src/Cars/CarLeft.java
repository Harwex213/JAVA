package Cars;

import java.util.concurrent.Semaphore;

import static Cars.Direction.*;

public class CarLeft extends Car
{


    public CarLeft(){
        QuantityCarLeft++;
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
