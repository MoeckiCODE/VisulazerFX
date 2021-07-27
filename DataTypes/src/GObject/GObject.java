package GObject;

import AOC.AOC;

import java.util.ArrayList;
/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public class GObject {
    //TODO rework variables to Private, replace dirket usages w/ getter and setter functions
    public  ArrayList<AOC> Objects;
    public ArrayList<Double> values;
    public String name;
    public Integer id;
    private static Integer nextID = 1;
    private static Integer nextIDLog = -100;
    private  ArrayList<String> nameforValues;


    public  GObject(String name) {

        this.name = name;
        if(!name.contains("Logic")) {
            this.id = nextID;
            nextID++;
        }else{
            this.id = nextIDLog;
            nextIDLog--;
        }

    }
    public  GObject(String name, Integer id) {

        this.name = name;
        this.id = id;

    }
    public static void reset(){
        nextID = 0;
    }
    public  ArrayList<String> getNameforValues() {
        return nameforValues;
    }

    public void addObjects(AOC object) {
        if(Objects == null)
            Objects = new ArrayList<>();
        Objects.add(object);
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

    public ArrayList<Double> getValues() {
        return values;
    }

    @Override
    public String toString(){
        return name + " ID:" + id;
    }
}

