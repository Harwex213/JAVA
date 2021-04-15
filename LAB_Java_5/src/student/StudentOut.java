package student;


import java.util.concurrent.Semaphore;

public class StudentOut extends Student
{
    public StudentOut(Semaphore doors, int id) {
        super(doors, id);
    }

    @Override
    public void run() {
        try {

            doors.acquire();
            System.out.println("Студент " + id + " заходит в дверь");

            sleep(2000);

            System.out.println("Студент " + id + " Студент вышел из университет");
            doors.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
