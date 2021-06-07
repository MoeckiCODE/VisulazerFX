package Action;


import java.util.ArrayList;

public class Action {
    ArrayList<Double> values;
    String actionName;
    Integer id;


    public Action(String actionName, Integer id) {
        this.values = values;
        this.actionName = actionName;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<Double> getValues() {
        return values;
    }
}
