package Transformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Analyse {
    private String transformationName;
    private Integer id;
    private List values;



    private int specsize;


    public synchronized List getValues() {
        return values;
    }

    public Analyse(String transformationName, Integer id) {
        values = Collections.synchronizedList(new ArrayList<>());
        this.transformationName = transformationName;
        this.id = id;
    }

    public String getTransformationName() {
        return transformationName;
    }

    public Integer getId() {
        return id;
    }

    public int getSpecsize() {
        return specsize;
    }

    public void setSpecsize(int specsize) {
        this.specsize = specsize;
    }

    public synchronized void  setValues(ArrayList<Double> values) {
        this.values = values;
    }

    @Override
    public String toString(){
        return transformationName + " ID:" + id;
    }
}

