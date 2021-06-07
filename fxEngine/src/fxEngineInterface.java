import Action.Action;
import GObject.GObject;

import java.util.ArrayList;

public interface fxEngineInterface {

    /**
     *
     * @return returns all actions given by the fxEngine
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
     * @return returns all GObjects given by the fxEngine
     */
    ArrayList<GObject> getGObjects();

    void doAcion(GObject gObjects, ArrayList<Action> actions);
}
