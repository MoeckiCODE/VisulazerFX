package mainFrameInterface;

import AOC.AOC;
import Action.Action;
import GObject.GObject;
import Logic.Logic;
import Transformation.Transformation;
import TransformationTrans.TransformationImplementation;


import java.util.ArrayList;

public class mainFrameImplementation implements mainFrameInterface {

    private  static ArrayList<Transformation> transformations;
    private static ArrayList<GObject> gObjects;
    private static ArrayList<Action> actions ;
    private  ArrayList<AOC> aocs;
    private static Logic log;
    private static TransformationImplementation trans;

    public mainFrameImplementation() {
        gObjects = new ArrayList<GObject>();
        actions  = new ArrayList<Action>();
        transformations = new ArrayList<Transformation>();
        log = new Logic();
        trans = new TransformationImplementation();
    }



    @Override
    public void setTransformation(ArrayList<Transformation> newtransformations) {
        transformations = newtransformations;
        /*if(!transformations.contains(transformation))
            transformations.add(transformation);
        if(transformations.contains(transformation))
            return true;
        else
            return false;*/
    }

    @Override
    public ArrayList<Transformation> getTransformations() {

        return transformations;
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
    public void removeAOC(AOC aoc){
        aocs.remove(aoc);
    }
    @Override
    public void  removeTransformetion(Transformation trans){
        transformations.remove(trans);
    }
public void saveallAOC(ArrayList<AOC> aocs){
    if(this.aocs == null)
        this.aocs = new ArrayList<AOC>();
    aocs.forEach(aoc -> {
        this.aocs.add(new AOC(aoc));
    });
    //this.aocs = aocs;
}
    @Override
    public boolean saveAOC(AOC aoc) {
        if(aocs == null)
            aocs = new ArrayList<AOC>();
            if(!aocs.contains(aoc))
                aocs.add(aoc);

        removedoublevalues(aoc);
       /*
        if(aocs == null)
            aocs = new ArrayList<AOC>();
        removedoublevalues(aoc);
        aocs.add(aoc);*/

        if(aocs.contains(aoc))
            return true;
        else return false;
    }
    public void removedoublevalues(AOC aoc){

        aocs.forEach(aoc1 -> {
            if(aoc1.toString() == aoc.toString())
                aocs.remove(aoc1);
        });


    }

    @Override
    public ArrayList<AOC> getAOC() {
        ArrayList<AOC> tmp = new ArrayList<AOC>();
        tmp.addAll(aocs);

        return tmp;
    }


    @Override
    public AOC getAoc(String name) {
        AOC tmp = new AOC( "FAIL", null, null);
        for (AOC aoc : aocs) {
            if(aoc.name == name)
            tmp = aoc;
        }
        return tmp;

    }

    @Override
    public void start() {

        log.initiate(log);
        transformations.addAll(trans.getTransformationTOMainFrame());
        actions.addAll(log.getActions());
        gObjects.addAll(log.getGObjects());

    }

    @Override
    public ArrayList<Double> getSignal() {
        return null;
    }

    @Override
    public void startup() {

        if(transformations != null && !transformations.isEmpty()) {
            trans.setTransformations(transformations);
            trans.startup();
        }
        log.setAOCs(aocs);
        log.setTransformation(transformations);
        log.start(log);

    }

    public void view(){
        log.setAOCs(aocs);
    }
    @Override
    public void stopit(){
        log.stoplogic();

    }
}
