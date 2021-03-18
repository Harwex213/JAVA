package TaxiFleet;

import taxi.Automobile;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@XmlRootElement
public class Fleet<T extends Automobile>  {

    @XmlElement(name = "taxi")
    private final ArrayList<Automobile> list = new ArrayList<>();

    public void clear(){
        list.clear();
    }

    public void add(Automobile obj){
        list.add(obj);
    }

    public int size(){
        return list.size();
    }

    public ArrayList<Automobile> getList(){
        return list;
    }

    public void searchUseStream(String value){
        Stream<Automobile> taxiStream = list.stream();
        taxiStream.map(Automobile::getName).
                filter(p->p.equals(value)).
                forEach(p->System.out.printf("Объект %s найден \n",p));

    }

    public List<String> sortUseStreamName(){
        Stream<Automobile> taxiStream = list.stream();
        return taxiStream.map(Automobile::getName).
                sorted().collect(Collectors.toList());
    }

    public void geaAvgFuelConsumption(){
        OptionalDouble AVG;
        AVG = list.stream().mapToDouble(Automobile::getFuelConsumption)
                .average();
        if(AVG.isPresent()){
            System.out.printf("Средний расход топлива парка: %.1f\n",AVG.getAsDouble());
        }
    }
}
