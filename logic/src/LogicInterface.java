
import Action.Action;

import java.util.ArrayList;

public interface LogicInterface {


        /**
         *
         * @return returns all Actions known by Logic
         */
        ArrayList<Action> getActions();

        /**java klasse aus anderem modul nutzen
         *
         * @return true if the start was successfully
         */
        boolean start();

        /**
         *
         * @return true if the stop was successfully
         */
        boolean stop();



    }
