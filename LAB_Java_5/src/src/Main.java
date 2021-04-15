package src;

import Cars.Car;
import Cars.CarLeft;
import Cars.CarRight;
import student.StudentIn;
import student.StudentOut;

import java.util.concurrent.Semaphore;

import static Cars.Direction.GetRandomGender;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Semaphore doors = new Semaphore(2);

        //for(int i=1;i<20;i = i+2) {
        //    new StudentIn(doors, i).start();
        //    new StudentOut(doors, i+1).start();
        //}

        for(int i=0;i<12;i++) {
            new CarLeft().start();
            new CarRight().start();
            //Thread.sleep(400);
        }
    }
}
