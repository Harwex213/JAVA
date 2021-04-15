package TaxiFleet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(namespace = "com.src.TaxiFleet.Fleet")
public class Fleet<T>  {
    @XmlElement(name = "taxi")
    ArrayList<T> list = new ArrayList<T>();

    public void clear(){
        list.clear();
    }

    public void add(T obj){
        list.add(obj);
    }
    public int size(){
        return list.size();
    }
    public ArrayList<T> getList(){
        return list;
    }
}
