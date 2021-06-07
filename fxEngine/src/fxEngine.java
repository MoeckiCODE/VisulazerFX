import Action.Action;

import GObject.GObject;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Comparator;


public class fxEngine extends Application implements fxEngineInterface {
    GObject cube = new GObject("Cube", 1);
    Action move = new Action("Move", 1);
    Action setColor = new Action("setColor", 2);


    private static final float WIDTH = 1280;
    private static final float HEIGHT = 720;
    long start;
    int counter;
    public int fps;
    boolean stop;
    private int boxmax;
    ArrayList<Cube> cubes = new ArrayList<>();
    ArrayList<Cube> boxesuse = new ArrayList<>();
    Scene scene;
    Group group;

    @Override
    public void start(Stage primaryStage) {

        group = new Group();

        Camera camera = new PerspectiveCamera();
        scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);

        group.translateXProperty().set(0);
        group.translateYProperty().set(0);
        group.translateZProperty().set(0);


        primaryStage.setTitle("Visulazer");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
        start = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
          if(!boxesuse.isEmpty())
                boxesuse.forEach(box1 -> {
                    box1.move();
                });
            }
        };
        timer.start();

    }

    @Override
    public ArrayList<Action> getActions() {
        ArrayList<Action> tmp = new ArrayList<>();
        tmp.add(move);
        tmp.add(setColor);
        return tmp;


    }

    @Override
    public boolean startfxE() {
        launch();
        return true;
    }

    @Override
    public boolean stopfxE() {

        try {
            stop();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }


    @Override
    public ArrayList<GObject> getGObjects() {
        ArrayList<GObject> tmp = new ArrayList<>();
        tmp.add(cube);
        return tmp;
    }

    @Override
    public void doAcion(GObject gObject, ArrayList<Action> actions) {
        Integer id = gObject.id;

        if(gObject.id == 1 && !(cubes.size() > gObject.id)){
            cubes.add(new Cube(gObject.values.get(0), scene, group, gObject.id, gObject.values.get(1), gObject.values.get(2), gObject.values.get(3)));

        }
        Cube c = cubes.get(gObject.id - 1);
        actions.forEach(action -> {
            switch (action.getId()){
                case 1: c.deltax = action.getValues().get(0);
                        c.deltay = action.getValues().get(1);
                        c.deltaz = action.getValues().get(2);
                case 2: c.color[0] = action.getValues().get(0);
                        c.color[1] = action.getValues().get(1);
                        c.color[2] = action.getValues().get(2);
            }

        });


    }


}
