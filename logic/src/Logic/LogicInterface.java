package Logic;

import AOC.AOC;
import Action.Action;
import GObject.GObject;
import java.util.ArrayList;

import Transformation.Transformation;
import fxEngine.fxEngine;
public interface LogicInterface {

public void addFxEngine(fxEngine fxE);

    public void setAOCs(ArrayList<AOC> aocs);

    public void initiate(Logic log);

    public void start(Logic log);

    public void stoplogic();

    public ArrayList<Action> getActions();

    public  ArrayList<GObject> getGObjects();

    public void setTransformation(ArrayList<Transformation> trans);





}



