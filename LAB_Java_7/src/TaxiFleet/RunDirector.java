package TaxiFleet;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class RunDirector extends Assert {
    private int sum;
    @Test
    void run() throws JAXBException, FileNotFoundException, InterruptedException {
        Outer outer = new Outer();
        DirectorFleet directorFleet = new DirectorFleet();

        outer.display("Сумма: ",String.valueOf(directorFleet.sum(1, 9)));
        outer.display("Количество объектов: ", String.valueOf(directorFleet.Deserialize()));
    }

}

class Outer{
    public void display(String msg, String val){
        System.out.println(msg + val);
    }
}
