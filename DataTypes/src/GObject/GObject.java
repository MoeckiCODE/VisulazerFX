package GObject;

import java.util.ArrayList;

public class GObject {
    public  ArrayList<GObject> Objects;
    public ArrayList<Double> values;
    public String name;
    public Integer id;
    private static Integer nextID = 0;
    private  ArrayList<String> nameforValues;


    public  GObject(String name) {

        this.name = name;
        this.id = nextID;
        nextID++;
    }
    public static void reset(){
        nextID = 0;
    }
    public  ArrayList<String> getNameforValues() {
        return nameforValues;
    }

    public  void setNameforValues(ArrayList<String> nameforValues) {
        this.nameforValues = nameforValues;
    }

    public void addtoList(){
        System.out.println("test");
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    @Override
    public String toString(){
        return name + " ID:" + id;
    }
}

