package Logic;

import AOC.AOC;
import Transformation.Transformation;
import javafx.application.Platform;
import Action.Action;
import GObject.GObject;

import fxEngine.fxEngine;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


public class Logic extends Thread implements LogicInterface{
//static Logic log;
static fxEngine fxint;
static ArrayList<Action> actions;
static ArrayList<GObject> gObjects;
static ArrayList<AOC> aocs;
static ArrayList<Transformation> transformations;
static Scene scene;
static boolean runs = false;
    public void start(){
        runs = true;
        super.start();

    }
//ToDo change coller -> langsames ändern
    @Override
    public ArrayList<Action> getActions() {
        return actions;
    }

    @Override
    public ArrayList<GObject> getGObjects() {
        return gObjects;
    }

    @Override
    public void setTransformation(ArrayList<Transformation> trans){
      transformations = trans;
    }

    @Override
    public void addFxEngine(fxEngine fxE){
        fxint = fxE;
    }

    @Override
    public void initiate(Logic log) {
        Action bc = new Action("BoderColision", 4);
        ArrayList<String> nfv = new ArrayList<>();
        nfv.add("null");
        nfv.add("null");
        nfv.add("null");
        bc.setNameForValues(nfv);
        Action changeColor = new Action("ChangeColor", 6);
        nfv = new ArrayList<>();
        nfv.add("color Picker");
        nfv.add("color Picker");
        nfv.add("Speed");
        changeColor.setNameForValues(nfv);
        runs = true;
        actions = new ArrayList<Action>();
        gObjects = new ArrayList<GObject>();

        fxint = new fxEngine();
        //ADD fxEngine Actions
        actions.addAll(fxint.getActions());
        //ADD logic Actions
        actions.add(bc);
        actions.add(changeColor);
        //ADD GObjects
        gObjects.addAll(fxint.getGObjects());
    }

    @Override
    public void start(Logic log) {
        runs = true;
        log.start();
        fxint.startfxE();

        System.out.println("lms");

    }
    @Override
    public void stoplogic(){
        fxint.stopfxE();
        fxint = new fxEngine();
        runs = false;
    }
    @Override
    public void setAOCs(ArrayList<AOC> aocs){
   /*     if(this.aocs == null)
            this.aocs = new ArrayList<AOC>();
        aocs.forEach(newaoc ->{
            if(!this.aocs.contains(newaoc))
            this.aocs.add(newaoc);
        } );*/
        this.aocs = aocs;

    }
private void doLogicAction(GObject go, ArrayList<Action> actions){
    actions.forEach(action -> {
                switch (action.getId()) {

                    case 6:
                        Double speed = action.getValues().get(0);
                        ArrayList<Color> ctmp = new ArrayList<>();
                        Color c1 = action.getColers().get(0);
                        Color c2 = action.getColers().get(1);
                        Color c3;
                        if(action.getColers().size() < 3){
                            c3 = c1;
                        }else
                           c3  = action.getColers().get(2);
                        ctmp.add(c1);
                        ctmp.add(c2);
                        Double diff = c3.getHue() - c2.getHue();
                        if(Math.abs(diff) >= 1) {
                            if (diff < 0) {
                                ctmp.add(c3.deriveColor(speed, 1, 1, 1));
                                action.setColers(ctmp);
                                System.out.println(diff);

                            }else if (diff > 0) {
                                ctmp.add(c3.deriveColor(speed*-1, 1, 1, 1));
                                action.setColers(ctmp);
                                System.out.println(diff);
                            }else{
                                //ctmp.add(c3.deriveColor(0, 1, 1, 1));
                                action.done = true;
                                System.out.println(diff);
                            }

                        }else action.done = true;
                }
            });
    fxint.doAcion(go, actions);
}
private void doSpec(GObject go ,Action action) {
    Transformation trans = transformations.get(0);
    //Construct the Objects for Spec/if there are no objectes created
    if(go.Objects == null) {
        //Creating Lines for every Dirac in spec
        for (Integer i = 1; i < trans.getSpecsize(); i++) {
           // ArrayList<GObject> tmp = new ArrayList<GObject>();

            GObject tmpGO = new GObject("Line");
            ArrayList<Double> values = new ArrayList<>();
            //Add values for the lines (x1, y1, x1, y2,) if value is null the index gets added so we get a automatic spread of the spectrum
            Integer finalI = i;
            go.values.forEach(v ->{
                if(v == null)
                    values.add(finalI.doubleValue());
                else
                    values.add(v);
            });
            //Add actions for each line (-> with coordinate should be differ according to the change in spec)
            Action change = new Action("fail", -2);
            ArrayList<Double> valuesforAction= new ArrayList<>();
            valuesforAction.add((Double) trans.getValues().get(i));
            //The changing coordinate is marked with a special value in this case -99
            if(go.values.get(0) == -99)
              change = new Action("changeX1", 1) ; //X1
            else if(go.values.get(1) == -99)
                change = new Action("changeX2", 2) ;
            else if(go.values.get(2) == -99)
                change = new Action("changeY1", 3) ;
            else if(go.values.get(3) == -99) {
                change = new Action("changeY2", 4);
            }
            change.setValues(valuesforAction);
            tmpGO.setValues(values);
            go.Objects.add(tmpGO);
        }
        //Action mode


    }



}
private void removedone(ArrayList<Action> actions){
        ArrayList<Action> tmp = new ArrayList<>();
        actions.forEach(action -> {

            if(action.done) {
                if(action.getColers() != null && action.getColers().size() == 3)
                    action.getColers().remove(2);
                action.done = false;
                tmp.add(action);
            }
        });
        actions.removeAll(tmp);


}

    public void run(){

        while (!fxint.runs ){
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

  while (fxint.runs && runs) {

      try {
          sleep(1);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      Platform.runLater(() -> {
          scene = fxint.givetheEngine();
          //System.out.println(aocs.size());

          aocs.forEach(aoc -> {
              if(!fxint.runs)
                  return;
              GObject go =  aoc.gObject;
              ArrayList<Action> actions = aoc.actions;
              scene.setOnKeyPressed(keyEvent -> {
                  String key = keyEvent.getCode().getChar();
                  System.out.println(key);
                  aoc.getHotleyActions().forEach(hotkeyaction -> {
                      String hotkey = hotkeyaction.getHotkey();
                      if(hotkey.equals(key)){
                          System.out.println("does");
                          actions.add(hotkeyaction);

                      }
                  });
              });
              doLogicAction(go, actions);
              removedone(actions);
              //actions.removeAll(aoc.getHotleyActions());

          });
                  });
    }

        System.out.println("log done");

    }



}





