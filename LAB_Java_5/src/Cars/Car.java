package Cars;

import java.util.concurrent.Semaphore;

import static Cars.Direction.Left;
import static Cars.Direction.Right;

public class Car  extends Thread{
    Semaphore road = new Semaphore(3,false);
    Semaphore left= new Semaphore(3,false);
    protected static Direction direction = Left;
    protected static int QuantityCar = 0;
    protected static int QuantityCarLeft;
    protected static int QuantityCarRight;

    synchronized public static void Switcher(){
        QuantityCar++;
        System.out.println("Машин слева: " + QuantityCarLeft + "; Машин справа: " + QuantityCarRight);

        System.out.println("-----------------------------------");

        if(direction == Left){
            System.out.println("Машин проехало слева: " + QuantityCar);
            if(QuantityCarLeft > 0)QuantityCarLeft--;
        }
        else {
            System.out.println("Машин проехало справа: " + QuantityCar);
            if(QuantityCarRight > 0)QuantityCarRight--;
        }


        System.out.println("-----------------------------------");

        if(QuantityCar == 3){
            if(direction == Left)
                direction = Right;
            else {
                direction = Left;
            }
            QuantityCar = 0;
        }

        if(direction == Left && QuantityCarLeft == 0){
            direction = Right;
            QuantityCar = 0;
        }
        else if(direction == Right && QuantityCarRight == 0){
            direction = Left;
            QuantityCar = 0;
        }
    }
}
