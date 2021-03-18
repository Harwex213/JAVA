package TaxiFleet;

import src.Main;
import taxi.Automobile;
import taxi.BusTaxi;
import taxi.Passenger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.logging.Logger;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class DirectorFleet implements Comparator<Automobile> {

    public int AllPriceFleet(Fleet<Automobile> list){
        int summ = 0;
        for (int i = 0; i < list.size(); i++) {
            Automobile taxi = list.getList().get(i);
            summ += taxi.getPrice();
        }
        return summ;
    }

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * The implementor must ensure that {@code sgn(compare(x, y)) ==
     * -sgn(compare(y, x))} for all {@code x} and {@code y}.  (This
     * implies that {@code compare(x, y)} must throw an exception if and only
     * if {@code compare(y, x)} throws an exception.)<p>
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * {@code ((compare(x, y)>0) && (compare(y, z)>0))} implies
     * {@code compare(x, z)>0}.<p>
     * <p>
     * Finally, the implementor must ensure that {@code compare(x, y)==0}
     * implies that {@code sgn(compare(x, z))==sgn(compare(y, z))} for all
     * {@code z}.<p>
     * <p>
     * It is generally the case, but <i>not</i> strictly required that
     * {@code (compare(x, y)==0) == (x.equals(y))}.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."<p>
     * <p>
     * In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(Automobile o1, Automobile o2) {
        Double a = o1.getFuelConsumption();
        Double b = o2.getFuelConsumption();
        return a.compareTo(b);
    }

    public void Serialize(Fleet<Automobile> list) throws JAXBException, FileNotFoundException {
        //писать результат сериализации в FileOutputStream

        FileOutputStream file = new FileOutputStream("D:\\University\\Labs\\JAVA\\LAB_Java_3\\src\\xml\\serializeTaxi.xml");
        Logger LOGGER = Main.getLOGGER();

        //создание объекта Marshaller
        JAXBContext context = JAXBContext.newInstance(Fleet.class, Automobile.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(list, file); // сериализация
    }

    public Fleet<Automobile> Deserialize(String fileName) throws JAXBException, FileNotFoundException{
        //открываем файл для чтения
        FileInputStream file = new FileInputStream(fileName);

        //создание объекта Marshaller
        JAXBContext context = JAXBContext.newInstance(Fleet.class, Passenger.class, BusTaxi.class);
        Unmarshaller marshaller = context.createUnmarshaller();
        return (Fleet<Automobile>)marshaller.unmarshal(file);
    }
}
