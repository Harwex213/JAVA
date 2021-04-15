package student;

import java.util.concurrent.Semaphore;

abstract class Student extends Thread {
    Semaphore doors;
    int id;

    Student(Semaphore doors, int id) {
        this.doors = doors;
        this.id = id;
    }

    abstract public void run();
}

