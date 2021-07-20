package gUI;

import Action.Action;
import GObject.GObject;
import Transformation.Transformation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainFrameInterface.mainFrameImplementation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Main extends Application {
    public static ArrayList<Action> actions = new ArrayList<>();
    public static ArrayList<GObject> gObjects = new ArrayList<>();
    public static ArrayList<Transformation> transformations = new ArrayList<>();
    public  static mainFrameImplementation mainframe;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Start Mainframe
        mainframe = new mainFrameImplementation();
        mainframe.start();
        actions.addAll(mainframe.getActions());
        gObjects = mainframe.getGObjects();
        transformations = mainframe.getTransformations();

        Collections.sort(actions, new Comparator<Action>() {
            @Override
            public int compare(Action o1, Action o2) {
                return  o1.id.compareTo(o2.id);
            }
        });
        //Initiate GUI
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Visulazier");
        primaryStage.setScene(new Scene(root, 1280, 720));
        Controller c = new Controller();

        primaryStage.show();
    }


    public static void main(String[] args) {

        Application.launch(args);
    }
}
