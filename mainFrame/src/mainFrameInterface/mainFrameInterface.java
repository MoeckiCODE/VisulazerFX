package mainFrameInterface;

import AOC.AOC;
import Action.Action;
import GObject.GObject;
import Transformation.Analyse;

import java.util.ArrayList;


public interface mainFrameInterface {

    /**
     *
     * @param aocs to be saved in the Mainframe also recreates all aocs and actions
     */
    public void saveallAOC(ArrayList<AOC> aocs);
    /**
     * sets the aocs to logic douring runtime
     */
    void view();
    /**
     *
     * @param analyses Analyses for Audiosignal
     *
     */
    void setAnalyses(ArrayList<Analyse> analyses);

    /**
     *
     * @return returns all signal analyses known
     */
    ArrayList<Analyse> getTransformations();


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

    /**
     *
     * @param analyse that should be removed
     */
    public void  removeTransformetion(Analyse analyse);
    /**
     * initiates the mainframe and the modules Logic, fxEngine
     */
    void initiate();


    /**
     * Starts the modules Logic, fxEngine and sets all Parameters
     */
    void startup();

    /**
     * stops the modules Logic and fxEngine
     */
    void stopit();

    /**
     *
     * @param aoc aoc that should be removed
     */
    void removeAOC(AOC aoc);

}
