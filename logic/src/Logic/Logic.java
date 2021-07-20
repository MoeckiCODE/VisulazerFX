package Logic;

import AOC.AOC;
import Transformation.Transformation;
import javafx.application.Platform;
import Action.Action;
import GObject.GObject;

import fxEngine.fxEngine;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;


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
        Action display = new Action("Logic.Display", 0);
        ArrayList<String> nfv = new ArrayList<>();
        nfv.add("null");
        nfv.add("null");
        nfv.add("null");
        display.setNameForValues(nfv);
        Action bc = new Action("Logic.BoderColision", 4);
        nfv = new ArrayList<>();
        nfv.add("null");
        nfv.add("null");
        nfv.add("null");
        bc.setNameForValues(nfv);
        Action changeColor = new Action("Logic.ChangeColor", 6);
        nfv = new ArrayList<>();
        nfv.add("color Picker");
        nfv.add("color Picker");
        nfv.add("Speed");
        changeColor.setNameForValues(nfv);
        Action dynamiccolor = new Action("Logic.DynamicColor.MeanColor", 11);
        nfv = new ArrayList<>();
        nfv.add("null");
        nfv.add("null");
        nfv.add("null");
        dynamiccolor.setNameForValues(nfv);
        GObject spectrum = new GObject("Logic.SimpleSpectrum", 100);
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
        spectrum.setNameforValues(nfv);
        runs = true;
        actions = new ArrayList<Action>();
        gObjects = new ArrayList<GObject>();

        fxint = new fxEngine();
        //ADD fxEngine Actions
        actions.addAll(fxint.getActions());
        //ADD logic Actions
        actions.add(display);
        actions.add(bc);
        actions.add(changeColor);
        actions.add(dynamiccolor);
        //ADD GObjects
        gObjects.addAll(fxint.getGObjects());
        gObjects.add(spectrum);
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
        if(go.name == "Logic.SimpleSpectrum")
            doSpec(go);
   // if(go.Objects != null && !go.Objects.isEmpty())
    AtomicReference<Action> newActionfromLogic = new AtomicReference<Action>(null);
    actions.forEach(action -> {
if(action != null){
                switch (action.getId()) {

                    case 6:
                        changecolor(go,action);
                        break;
                    case 11:
                        newActionfromLogic.set(dynamicColorChange(go, actions));


            }}});
    if(newActionfromLogic.get() != null)
    actions.add(newActionfromLogic.get());
    fxint.doAcion(go, actions);
}
private Action dynamicColorChange(GObject go, ArrayList<Action> actions){
        if(go.name.contains("Cube")){
    Transformation trans = transformations.get(0);
    ArrayList<Float> tmpList = new ArrayList<>();
    tmpList.addAll(transformations.get(0).getValues());
    double maxv = 0;
    float colorhsbvalue = 0;
    for (Integer i = 0; i < trans.getSpecsize(); i++) {
        double hsbv = 0;
        double j = (float) i;


        if(i <= 6){
            hsbv = tmpList.get(i) * ((j * 43.0)/260 )* 360.0;
            //bgc = Color.hsb(((i * 43)/246)*360, 1, 0.5);
        }else if(i<=12){
            hsbv =tmpList.get(i)*(((6*43))/516)* 360;
            // bgc = Color.hsb(((i * 43)/492)*360, 1, 0.6);
        }else if(i<= 23){
            hsbv =tmpList.get(i)*(((11*43))/990 )* 360;
            //bgc = Color.hsb(((i * 43)/985)*360, 1, 0.7);

        }else if(i<= 45){
            hsbv =tmpList.get(i)*(((22*43))/1935)* 360;
            // bgc = Color.hsb(((i * 43)/1968)*360, 1, 0.8);
        }else if(i<=92){
            hsbv =tmpList.get(i)*(((44*43))/3956 )* 360;
            // bgc = Color.hsb(((i * 43)/3936)*360, 1, 0.9);
        }else if(i<=183){
            hsbv =tmpList.get(i)*(((91*43))/7869 )* 360;
            // bgc = Color.hsb(((i * 43)/7872)*360, 1, 1);
        }else if(i<=366){
            hsbv =tmpList.get(i)*(((182*43))/15738 )* 360;
            // bgc = Color.hsb(((i * 43)/15744)*360, 1, 1);

        }else if(i<=513){
            hsbv = tmpList.get(i)*(((j * 43)-(365*43))/22059) * 360;
            // bgc = Color.hsb(((i * 43)/22059)*360, 1, 1);

        }
        if(hsbv > maxv)
            maxv = hsbv;
        colorhsbvalue += hsbv;
    }
    Color tmp = Color.hsb(colorhsbvalue/513,1,1);
    Action tmpA = new Action("setColor", 2);
    ArrayList tmpvalues = new ArrayList();
    tmpvalues.add(tmp.getRed());
    tmpvalues.add(tmp.getGreen());
    tmpvalues.add(tmp.getBlue());
    tmpA.setValues(tmpvalues);

return tmpA;
        }
        return null;
    }




private void changecolor(GObject go, Action action){
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
            //  System.out.println(diff);

        }else if (diff > 0) {
            ctmp.add(c3.deriveColor(speed*-1, 1, 1, 1));
            action.setColers(ctmp);
            //  System.out.println(diff);
        }else{
            //ctmp.add(c3.deriveColor(0, 1, 1, 1));
            action.done = true;
            //   System.out.println(diff);
        }

    }else action.done = true;
}

private void doSpec(GObject go) {
    Transformation trans = transformations.get(0);
    //Construct the Objects for Spec/if there are no objectes created
    if(go.Objects == null) {
        //Creating Line for every Dirac in spec
        for (Integer i = 0; i < trans.getSpecsize(); i++) {
           // ArrayList<GObject> tmp = new ArrayList<GObject>();

            GObject tmpGO = new GObject("fxEngine.Line");
            ArrayList<Double> values = new ArrayList<>();
            //Add values for the line (x1, y1, x1, y2,) if value is null the index gets added so we get a automatic spread of the spectrum
            Integer finalI = i;
            go.values.forEach(v ->{
                if(v == null)
                    values.add(finalI.doubleValue());
                else
                    values.add(v);
            });
            //Set position values to single line
            tmpGO.setValues(values);
            //Add actions for each line (-> with coordinate should be differ according to the change in spec)
            Action change = new Action("fail", -2);
            ArrayList<Double> valuesforAction= new ArrayList<>();

            valuesforAction.add(Double.valueOf(trans.getValues().get(i).toString()));
            //The changing coordinate is marked with a special value in this case -99
            if(tmpGO.values.get(0) == -99) {
                change = new Action("changeX1", 7); //X1
            }else if(tmpGO.values.get(1) == -99) {
                change = new Action("changeY1", 8); //Y2
            }else if(tmpGO.values.get(2) == -99){
                change = new Action("changeX2", 9); //X2
            }else if(tmpGO.values.get(3) == -99) {
                change = new Action("changeY2", 10); //Y2
            }
            //Set the change parameter to single Line
            change.setValues(valuesforAction);
            ArrayList<Action> linechange= new ArrayList<>();
            linechange.add(change);

            //Create AOC for Line
            go.addObjects(new AOC("SpecLine " + i, tmpGO, linechange));
        }


        //Action mode


    }
    AtomicReference<Double> diffValue = new AtomicReference<>(0d);
    go.values.forEach(v -> {
        if(v != null && v != -99)
            diffValue.set(v);
    });

    for(int i = 0; i < trans.getSpecsize(); i++) {
        ArrayList<Double> valuesforAction= new ArrayList<>();
        Float tmp =(Float) trans.getValues().get(i);


        Double newValue = diffValue.get() - Double.valueOf(tmp.doubleValue());
        valuesforAction.add(newValue);
        go.Objects.get(i).actions.get(0).setValues(valuesforAction);
    }
    go.Objects.forEach(aoc -> {

        fxint.doAcion(aoc.gObject, aoc.actions);
    });


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
//              System.out.println(actions.contains(new Action("fxEngine.Move", 1)));
              if(actions.contains(new Action("Logic.Display", 0)))
              doLogicAction(go, actions);
              removedone(actions);
              //actions.removeAll(aoc.getHotleyActions());

          });
                  });
    }

        System.out.println("log done");

    }



}





