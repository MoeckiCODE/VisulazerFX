package Logic;

import javafx.application.Platform;
import Action.Action;
import GObject.GObject;
import fxEngine.fxEngine;
import fxEngine.fxEngineInterface;
import java.util.ArrayList;
public class Logic extends Thread implements LogicInterface{
static Logic log;

static fxEngine fxint;

    public static void main(String[] args) throws InterruptedException {
        log = new Logic();
        fxint = new fxEngine();
        log.start();

        fxint.startfxE();

        System.out.println("lms");






    }
    public void start(){
        super.start();

    }
    public void run(){

        while (!fxint.runs){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            Platform.runLater(() -> {

                GObject go = new GObject("Cube", 1);
                ArrayList<Double> tmp = new ArrayList<>();
                tmp.add(50.0);
                tmp.add(500.0);
                tmp.add(500.0);
                tmp.add(100.0);
                go.setValues(tmp);
                Action a = new Action("move", 1);
                tmp = new ArrayList<>();
                tmp.add(1.0);
                tmp.add(1.0);
                tmp.add(1.0);
                a.setValues(tmp);
                ArrayList<Action> ac = new ArrayList<>();
                ac.add(a);
                fxint.doAcion(go, ac);

            });


    }
@Override
  public void addFxEngine(fxEngine fxE){
        fxint = fxE;

}


    }





