package mainFrameInterface;

import AOC.AOC;
import Action.Action;
import GObject.GObject;
import Logic.Logic;
import Transformation.Transformation;

import java.util.ArrayList;

public interface mainFrameInterface {
    /**
     *
     * @param transformation Transformation thats added to the Signalflow
     * @return true if successfully
     */
    public void setTransformation(ArrayList<Transformation> transformations);

    /**
     *
     * @return returns all transformations known
     */
    ArrayList<Transformation> getTransformations();

    public void removeTransformetion(Transformation trans);

    /**
     *
     * @return returns all actions known
     */
    ArrayList<Action> getActions();

    /**
     *
     * @return returns all known GObjects
     */
    ArrayList<GObject> getGObjects();

    /**
     *
     * @param aoc the Object-Action-combination that soud be saved
     * @return returns true if the objected has saved
     */
    boolean saveAOC(AOC aoc);

    /**
     *
     * @return returns all Object-Action-combination with are active
     */
    ArrayList<AOC> getAOC();

    /**
     *
     * @param name name from the Object-Action-combination with is wanted
     * @return returns the Object-Action-combination for the specified name
     */
    AOC getAoc(String name);

    void start();

    ArrayList<Double> getSignal();

void startup();
void stopit();
void removeAOC(AOC aoc);

}
