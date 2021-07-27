package Logic;

import AOC.AOC;
import Action.Action;
import GObject.GObject;
import java.util.ArrayList;

import Transformation.Analyse;
/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public interface LogicInterface {



    /**
     * sets the Action Object Combinations on the Logic Module
     * @param aocs to be executed
     */
    void setAOCs(ArrayList<AOC> aocs);

    /**
     * initiates the logic Module
     * @param log
     */

    void initiate(Logic log);

    /**
     * Startup of the logic Module
     * @param log
     */
    void start(Logic log);

    /**
     * Stops the logicmodule
     */
    void stoplogic();

    /**
     *
     * @return all actions the Logicmodule knows
     */
    ArrayList<Action> getActions();

    /**
     *
     * @return all objects the Logicmodule knows
     */
    ArrayList<GObject> getGObjects();

    /**
     *
     * @param trans will be set to calculate objcets
     */
    void setTransformation(ArrayList<Analyse> trans);





}



