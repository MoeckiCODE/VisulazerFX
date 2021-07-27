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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public class fxEngine  implements fxEngineInterface {
    static GObject cube = new GObject("fxEngine.Cube");
    static GObject line = new GObject("fxEngine.Line");
    static Action move = new Action("Move", 1);
    static Action setColor = new Action("setColor", 2);
    static Action translate = new Action("changes", 3);

    static Action changeX1 = new Action("fxEngine.changeX1", 7) ; //X1
    static Action changeY1 = new Action("fxEngine.changeY1", 8); //Y2
    static Action changeX2 = new Action("fxEngine.changeX2", 9); //X2
    static Action changeY2 = new Action("fxEngine.changeY2", 10); //Y2

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

    /**
     *
     * @return width for the scene
     */
    public static float getWIDTH() {
        return WIDTH;
    }

    /**
     *
     * @return Height of the scene
     */
    public static float getHEIGHT() {
        return HEIGHT;
    }

    /**
     * Constructor for the Module, Creates the static objects
     */
    public fxEngine() {
        //Create Action & Objecs for fxEngine
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
        nfv.add("X1");
        nfv.add("Y1");
        nfv.add("null");

        nfv.add("X2");
        nfv.add("Y2");
        nfv.add("null");

        nfv.add("null");
        nfv.add("null");
        nfv.add("null");
        line.setNameforValues(nfv);
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
        nfv.add("Value");
        nfv.add("null");
        nfv.add("null");
        changeY1.setNameForValues(nfv);
        changeY2.setNameForValues(nfv);
        changeX1.setNameForValues(nfv);
        changeX2.setNameForValues(nfv);

    }


    @Override
    public ArrayList<Action> getActions() {
        ArrayList<Action> tmp = new ArrayList<>();
        tmp.add(move);
        tmp.add(setColor);
        tmp.add(changeX1);
        tmp.add(changeY1);
        tmp.add(changeX2);
        tmp.add(changeY2);
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

        start = System.currentTimeMillis();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if(!runs)
                    return;

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
        tmp.add(line);
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
        AtomicInteger index = new AtomicInteger(-1);
        cubes.forEach(cu ->{
            if(cu.getClass() == Line.class) {
                Line tmp = (Line) cu;
                if (tmp.id == id)
                    index.set(cubes.indexOf(cu));
            }
        });
        if (index.get() == -1) { //if (!(cubes.size() >= gObject.id))
            Line tmpnew = new Line(gObject.values.get(0), gObject.values.get(1), gObject.values.get(2), gObject.values.get(3), gObject.id, scene, group);
            cubes.add(tmpnew);
            index.set(cubes.indexOf(tmpnew));
            System.out.println("new line");
        }
        Line l = (Line) cubes.get(index.get());
        actions.forEach(action -> {

            switch (action.getId()){
                case 7:

                    l.setX1(action.getValues().get(0));
                    break;
                case 8:
                    l.setY1(action.getValues().get(0));
                    break;
                case 9:
                    l.setX2(action.getValues().get(0));
                    break;
                case 10:
                    l.setY2(action.getValues().get(0));
                    break;
            }




        });


//TODO change, remove
    }

    private void doActionForCube(GObject gObject, ArrayList<Action> actions){
        Integer id = gObject.id;
        AtomicInteger index = new AtomicInteger(-1);
        cubes.forEach(cu ->{
            if(cu.getClass() == Cube.class) {
                Cube tmp = (Cube) cu;
                if (tmp.id == id)
                    index.set(cubes.indexOf(cu));
            }
        });
        if (index.get() == -1) { //if (!(cubes.size() >= gObject.id))
            Cube tmpnew = new Cube(gObject.values.get(0), scene, group, gObject.id, gObject.values.get(1), gObject.values.get(2), gObject.values.get(3));
            cubes.add(tmpnew);
            index.set(cubes.indexOf(tmpnew));
            System.out.println("new cube");
        }
        Cube c = (Cube)cubes.get(id - 1);
ArrayList<Action> actionremove = new ArrayList<>();
        actions.forEach(action -> {
            switch (action.getId()){
                case 1:
                    c.deltax = action.getValues().get(0)/10;
                    c.deltay = action.getValues().get(1)/10;
                    c.deltaz = action.getValues().get(2)/10;
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
