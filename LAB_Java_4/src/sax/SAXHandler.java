package sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import taxi.Automobile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SAXHandler extends DefaultHandler implements Comparator {

    public List<Automobile> empList = new ArrayList<>();
    Automobile emp = null;
    String content = null;

    /**
     * Receive notification of the beginning of the document.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the beginning
     * of a document (such as allocating the root node of a tree or
     * creating an output file).</p>
     *
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#startDocument
     */
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing");
    }

    /**
     * Receive notification of the end of the document.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end
     * of a document (such as finalising a tree or closing an output
     * file).</p>
     *
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#endDocument
     */
    @Override
    public void endDocument() throws SAXException {
        show();
        System.out.println("End parsing");
    }

    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        //Create a new Employee object when the start tag is found
        if ("taxi".equals(qName)) {
            emp = new Automobile();
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        switch(qName){
            //Add the employee to list once end tag is found
            case "taxi":
                empList.add(emp);
                break;
            //For all other end tags the employee has to be updated.
            case "currentFuelCapacity":
                emp.setCurrentFuelCapacity(new Integer(content));
                break;
            case "fuelConsumption":
                emp.setFuelConsumption(new Double(content));
                break;
            case "maxSpeed":
                emp.setMaxSpeed(new Integer(content));
                break;
            case "price":
                emp.setPrice(new Integer(content));
                break;
            case "name":
                emp.setName(content);
                break;

        }

    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.<p>
     * <p>
     * The implementor must ensure that <tt>sgn(compare(x, y)) ==
     * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>compare(x, y)</tt> must throw an exception if and only
     * if <tt>compare(y, x)</tt> throws an exception.)<p>
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
     * <tt>compare(x, z)&gt;0</tt>.<p>
     * <p>
     * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
     * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
     * <tt>z</tt>.<p>
     * <p>
     * It is generally the case, but <i>not</i> strictly required that
     * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
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
    public int compare(Object o1, Object o2) {
        Automobile auto1 = null;
        Automobile auto2 = null;

        if(o1 instanceof Automobile){
            auto1 =  (Automobile)o1;
        }

        if(o1 instanceof Automobile){
            auto2 = (Automobile)o2;
        }
        assert auto1 != null;
        return auto1.getName().compareTo(auto2.getName());
    }

    public void show(){

        for (Automobile taxi:empList) {
            System.out.printf("Производитель: %s, стоимость: %d, запас топлива %d\n",
                    taxi.getName(),
                    taxi.getPrice(),
                    taxi.getCurrentFuelCapacity()
            );
        }
    }

    public void sort(){
        empList.sort(Automobile::compareTo);
    }
}