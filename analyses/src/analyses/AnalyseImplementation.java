package analyses;
import Transformation.Analyse;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;

import java.util.ArrayList;

/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public class AnalyseImplementation extends Thread implements AnalyseInterface {
    private ArrayList<Analyse> analyses;
    private boolean runs = false;
    private static Minim minim;
    private static AudioInput input;
    private static FFT fft;


    /**
     * Constructor for new Analyses
     */
    public AnalyseImplementation() {
        analyses = new ArrayList<Analyse>();
    }




    @Override
    public ArrayList<Analyse> getAnalysesTOMainFrame() {
        ArrayList tmp = new ArrayList();
        Analyse transTmp = new Analyse("FFT", 1);
        tmp.add(transTmp);

        return tmp;
    }

    @Override
    public void setAnalyses(ArrayList<Analyse> analyses) {
            this.analyses = analyses;
    }

    public void startup() {
        if(minim == null) {
        minim = new Minim(this);
        input = minim.getLineIn();
        fft = new FFT(input.bufferSize(), input.sampleRate());
        }
        runs = true;
        super.start();
    }

    public void stopit(){

        runs = false;
    }



     public void run() {
        while (runs) {
            analyses.forEach(transformation -> {

                switch (transformation.getId()){

                    case 1 :
                        fft.forward(input.mix);
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
