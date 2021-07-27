package Action;


import javafx.scene.paint.Color;

import java.util.ArrayList;
/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public class Action {
    //TODO rework variables to Private, replace dirket usages w/ getter and setter functions
    ArrayList<Double> values;
   public String actionName;
   public Integer id;
   private  ArrayList<String> nameForValues;
    private ArrayList<Color> colers;
    public boolean done = false;
    private String hotkey;

    public Action(String actionName, Integer id) {
        boolean done = false;
        this.actionName = actionName;
        this.id = id;
    }

    /**
     * Constructor to copy an action
     * @param a the Action that should be Copyed
     */
    public Action(Action a){
        this.values = new ArrayList<>();
        if(a.values != null)
        this.values.addAll(a.values);
        this.actionName = a.actionName;
        this.id = a.id;
        this.nameForValues = a.nameForValues;
        this.hotkey = a.getHotkey();
        if(a.colers != null ){
        this.colers = new ArrayList<>();
        colers.add(a.colers.get(0).deriveColor(0,1,1,1));
        colers.add(a.colers.get(1).deriveColor(0,1,1,1));
        boolean done = false;
    }}

    /**
     *
     * @return the collers stored in the action
     */
    public ArrayList<Color> getColers() {
        return colers;
    }

    /**
     *
     * @param colers sets collers to action
     */
    public void setColers(ArrayList<Color> colers) {
        this.colers = colers;
    }



    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Action tmp = (Action) obj;
        if(id == tmp.id)
            return true;


        return false;
    }
    public String getHotkey() {
        return hotkey;
    }

    public void setHotkey(String hotkey) {
        this.hotkey = hotkey;
    }



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
