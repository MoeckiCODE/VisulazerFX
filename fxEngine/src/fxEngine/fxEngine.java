package fxEngine;

import Action.Action;

import GObject.GObject;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



import java.util.ArrayList;


public class fxEngine  implements fxEngineInterface {
    static GObject cube = new GObject("fxEngine.Cube");
    static GObject line = new GObject("fxEngine.Line");
    static Action move = new Action("Move", 1);
    static Action setColor = new Action("setColor", 2);
    static Action translate = new Action("changes", 3);


    private static final float WIDTH = 1280;
    private static final float HEIGHT = 720;
    long start;
    int counter;
    public int fps;
    public static boolean runs = false;
    private int boxmax;
    static ArrayList<Object> cubes = new ArrayList<>();
    static ArrayList<Line> Lines = new ArrayList<>();
    static ArrayList<Cube> boxesuse = new ArrayList<>();
    static Scene scene;
    static Group group;
    Stage stage;

    public fxEngine() {
        ArrayList<String> nfv = new ArrayList<>();
        nfv.add("Size");
        nfv.add("null");
        nfv.add("null");

        nfv.add("pos X");
        nfv.add("pos Y");
        nfv.add("pos Z");

        nfv.add("null");
        nfv.add("null");
        nfv.add("null");
        cube.setNameforValues(nfv);

        nfv = new ArrayList<>();
        nfv.add("Speed X");
        nfv.add("Speed Y");
        nfv.add("Speed Z");
        move.setNameForValues(nfv);
        nfv = new ArrayList<>();
        nfv.add("R");
        nfv.add("G");
        nfv.add("B");
        setColor.setNameForValues(nfv);

        nfv = new ArrayList<>();
        nfv.add("X1");
        nfv.add("Y1");
        nfv.add("null");

        nfv.add("X2");
        nfv.add("Y2");
        nfv.add("null");

        nfv.add("null");
        nfv.add("null");
        nfv.add("null");
    }
    /* @Override
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
      //  primaryStage.setFullScreen(true);
        start = System.currentTimeMillis();

      AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                boxesuse = cubes;
          if(!boxesuse.isEmpty()){
                boxesuse.forEach(box1 -> {
                   // box1.move();
                });
            }
              //  System.out.println("test");
                }
        };
        timer.start();
        runs = true;
    }*/

    @Override
    public ArrayList<Action> getActions() {
        ArrayList<Action> tmp = new ArrayList<>();
        tmp.add(move);
        tmp.add(setColor);
        return tmp;


    }

    @Override
    public boolean startfxE() {


        runs = true;
        group = new Group();
        stage =  new Stage();
        Camera camera = new PerspectiveCamera();
        scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);

        group.translateXProperty().set(0);
        group.translateYProperty().set(0);
        group.translateZProperty().set(0);

        stage.setTitle("Visulazer");
        stage.setScene(scene);
        stage.show();
        //  primaryStage.setFullScreen(true);
        start = System.currentTimeMillis();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if(!runs)
                    return;
               // System.out.println(cubes.size());
              /*  boxesuse = cubes;
                if(!boxesuse.isEmpty()){
                    boxesuse.forEach(box1 -> {
                        // box1.move();
                    });}*/

                //System.out.println("test");
            }
        };
        timer.start();
        return true;
    }

    @Override
    public boolean stopfxE() {

        try {
            runs = false;
            group.getChildren().removeAll(cubes);
            stage.close();
            cubes = new ArrayList<>();
            System.out.println("no more cubes");


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

   switch (gObject.name) {
       case "fxEngine.Cube":
           doActionForCube(gObject, actions);
           break;
       case "fxEngine.Line" :
           doActionForLine(gObject, actions);
           break;
   }
    }
    private void doActionForLine(GObject gObject, ArrayList<Action> actions){
        Integer id = gObject.id;
        if (!(cubes.size() >= gObject.id)) {
            cubes.add(new Cube(gObject.values.get(0), scene, group, gObject.id, gObject.values.get(1), gObject.values.get(2), gObject.values.get(3)));
            System.out.println("Line");
        }

    }

    private void doActionForCube(GObject gObject, ArrayList<Action> actions){
        Integer id = gObject.id;
        if (!(cubes.size() >= gObject.id)) {
            cubes.add(new Cube(gObject.values.get(0), scene, group, gObject.id, gObject.values.get(1), gObject.values.get(2), gObject.values.get(3)));
            System.out.println("new cube");
        }
        Cube c = (Cube)cubes.get(id - 1);
ArrayList<Action> actionremove = new ArrayList<>();
        actions.forEach(action -> {
            switch (action.getId()){
                case 1:
                    c.deltax = action.getValues().get(0)/10.0;
                    c.deltay = action.getValues().get(1)/10.0;
                    c.deltaz = action.getValues().get(2)/10.0;
                    c.move();
                    if(action.getHotkey() != "")
                    action.done = true;
                    if(c.maxx> c.bla.getWidth()   || c.minx < 1) {
                        action.getValues().set(0, c.deltax*-1);
                    }
                    if(c.maxy> c.bla.getHeight()   || c.miny < 1) {
                        action.getValues().set(1, c.deltay*-1);
                    }
                    if(c.maxz > 10|| c.minz < -3000) {
                        action.getValues().set(2, c.deltaz*-1);
                    }


                    break;

                case 2:
                    c.color[0] = action.getValues().get(0);
                    c.color[1] = action.getValues().get(1);
                    c.color[2] = action.getValues().get(2);
                    actionremove.add(action);
                    c.changecolor();

                    break;
                case 3:

                    c.x = gObject.values.get(1);
                    c.y = gObject.values.get(2);
                    c.z = gObject.values.get(3);
                    c.size = gObject.values.get(0);
                   action.id = -1;

                   c.change();
                    break;
                case 5:
                    group.getChildren().remove(c.box);
                    cubes.remove(c);

                   // action.id = -1;
                case 6:
                    if(action.getColers().size() == 3)
                    c.setColor(action.getColers().get(2));

                default:
                    break;
            }
        });
        actions.removeAll(actionremove);
    }
    @Override
    public Scene givetheEngine(){
        return scene;
    }


}
