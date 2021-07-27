package mainFrameInterface;

import AOC.AOC;
import Action.Action;
import GObject.GObject;
import Logic.Logic;
import Transformation.Analyse;
import analyses.AnalyseImplementation;


import java.util.ArrayList;
/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public class mainFrameImplementation implements mainFrameInterface {

    private  static ArrayList<Analyse> analyses;
    private static ArrayList<GObject> gObjects;
    private static ArrayList<Action> actions ;
    private  ArrayList<AOC> aocs;
    private static Logic log;
    private static AnalyseImplementation analyse;

    /**
     * Construktor for mainframeimplementation
     */
    public mainFrameImplementation() {
        gObjects = new ArrayList<GObject>();
        actions  = new ArrayList<Action>();
        analyses = new ArrayList<Analyse>();
        log = new Logic();
        analyse = new AnalyseImplementation();
    }



    @Override
    public void setAnalyses(ArrayList<Analyse> newtransformations) {
        analyses = newtransformations;
        /*if(!transformations.contains(transformation))
            transformations.add(transformation);
        if(transformations.contains(transformation))
            return true;
        else
            return false;*/
    }

    @Override
    public ArrayList<Analyse> getTransformations() {

        return analyses;
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
    public void  removeTransformetion(Analyse analyse){
        analyses.remove(analyse);
    }
    @Override
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
    private void removedoublevalues(AOC aoc){

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
    public void initiate() {

        log.initiate(log);
        analyses.addAll(analyse.getAnalysesTOMainFrame());
        actions.addAll(log.getActions());
        gObjects.addAll(log.getGObjects());

    }



    @Override
    public void startup() {

        if(analyses != null && !analyses.isEmpty()) {
            analyse.setAnalyses(analyses);
            analyse.startup();
        }
        log.setAOCs(aocs);
        log.setTransformation(analyses);
        log.start(log);

    }
@Override
    public void view(){
        log.setAOCs(aocs);
    }
    @Override
    public void stopit(){
        log.stoplogic();
        log = new Logic();
        analyse.stopit();

    }
}
