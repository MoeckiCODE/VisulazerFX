package Action;


import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Action {
    ArrayList<Double> values;
   public String actionName;
   public Integer id;
   private  ArrayList<String> nameForValues;
    private ArrayList<Color> colers;
    public boolean done = false;


    public Action(String actionName, Integer id) {
        boolean done = false;
        this.actionName = actionName;
        this.id = id;
    }
    public Action(Action a){
        this.values = a.values;
        this.actionName = a.actionName;
        this.id = a.id;
        this.nameForValues = a.nameForValues;
        if(a.colers != null ){
        this.colers = new ArrayList<>();
        colers.add(a.colers.get(0).deriveColor(0,1,1,1));
        colers.add(a.colers.get(1).deriveColor(0,1,1,1));
        boolean done = false;
    }}

    public ArrayList<Color> getColers() {
        return colers;
    }

    public void setColers(ArrayList<Color> colers) {
        this.colers = colers;
    }





    public String getHotkey() {
        return hotkey;
    }

    public void setHotkey(String hotkey) {
        this.hotkey = hotkey;
    }

    private String hotkey;

    public Integer getId() {
        return id;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> tmp) {
        values = tmp;
    }

    public  void setNameForValues(ArrayList<String> nameForValues) {
        this.nameForValues = nameForValues;
    }

    public  ArrayList<String> getNameForValues() {
        return nameForValues;
    }

    @Override
    public String toString(){
        return actionName + " ID:" + id;
    }
}
