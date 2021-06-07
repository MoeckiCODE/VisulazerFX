package GObject;

import java.util.ArrayList;

public class GObject {
    //public static ArrayList<GObject> Objects;
    public ArrayList<Double> values;
    public String name;
    public Integer id;

    public  GObject( String name, Integer id) {

        this.name = name;
        this.id = id;
    }
    public void addtoList(){
        System.out.println("test");
    }
}

