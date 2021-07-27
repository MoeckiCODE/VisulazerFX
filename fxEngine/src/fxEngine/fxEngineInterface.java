package fxEngine;

import Action.Action;
import GObject.GObject;
import javafx.scene.Scene;

import java.util.ArrayList;
/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public interface fxEngineInterface {

    /**
     *
     * @return returns all actions given by the fxEngine.fxEngine
     */
    ArrayList<Action> getActions();

    /**
     *
     * @return true if the start was successfully
     */
    boolean startfxE();

    /**
     *
     * @return true if the stop was successfully
     */
    boolean stopfxE();

    /**
     *
     * @return returns all GObjects given by the fxEngine.fxEngine
     */
    ArrayList<GObject> getGObjects();

    /**
     *
     * @param gObjects on with the actions should be executed
     * @param actions that should be executed
     */
    void doAcion(GObject gObjects, ArrayList<Action> actions);

    /**
     * !JUST NEEDED FOR HOTKEYIMPLEMENTATION!
     * @return the scene of the Visulazer to execute Hotekeys
     */
     Scene givetheEngine();
}
