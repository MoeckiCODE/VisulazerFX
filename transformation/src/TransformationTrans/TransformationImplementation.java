package TransformationTrans;
import Transformation.Transformation;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TransformationImplementation extends Thread implements TransformationInterface {
    ArrayList<Transformation> transformations;
    boolean runs = false;

    Minim minim = new Minim(this);
    AudioInput input = minim.getLineIn();
    FFT fft = new FFT(input.bufferSize(), input.sampleRate());



    public TransformationImplementation() {
        transformations = new ArrayList<Transformation>();
    }




    @Override
    public ArrayList<Transformation> getTransformationTOMainFrame() {
        ArrayList tmp = new ArrayList();
        Transformation transTmp = new Transformation("FFT", 1);
        tmp.add(transTmp);

        return tmp;
    }

    @Override
    public void setTransformations(ArrayList<Transformation> transformations) {
            this.transformations = transformations;
    }

    public void startup() {
        runs = true;
        super.start();
    }

    public void stopit(){
        runs = false;
    }



    public void run() {


        while (runs) {
            transformations.forEach(transformation -> {

                switch (transformation.getId()){

                    case 1 :
                        fft.forward(input.mix);
                        transformation.setSpecsize(fft.specSize());
                        transformation.setSpecsize(fft.specSize());
                        ArrayList tmp = new ArrayList();
                        for (int i = 0; i <= fft.specSize(); i++) {
                            tmp.add(fft.getBand(i));
                        }
                        transformation.setValues(tmp);
                    break;
                    default:
                        break;
                }


            });
        }


    }
}
