package AOC;

import Action.Action;
import GObject.GObject;

import java.util.ArrayList;

public class AOC {
    private static Integer newID = 1;
   public Integer id;
   public String name;
   public GObject gObject;
   public ArrayList<Action> actions;
    public ArrayList<Action> hotleyActions;
    public ArrayList<Action> getHotleyActions() {
        return hotleyActions;
    }

    public AOC(AOC aoc) {
        this.id = aoc.id;
        this.name = aoc.name;
        this.gObject = aoc.gObject;
        this.actions = new ArrayList<Action>();
        aoc.actions.forEach(action -> {
            this.actions.add(new Action(action));
        });
        this.hotleyActions = aoc.hotleyActions;
    }

    public void setHotleyActions(ArrayList<Action> hotleyActions) {
        this.hotleyActions = hotleyActions;
    }



    public AOC(String name, GObject gObject, ArrayList<Action> actions) {
        this.id = newID;
        this.name = name;
        this.gObject = gObject;
        this.actions = actions;
        newID++;
    }
    @Override
    public String toString(){
        return name + " ID: " + id + " " + gObject.name ;
    }
}
