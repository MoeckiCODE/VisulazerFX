package analyses;

import Transformation.Analyse;

import java.util.ArrayList;
/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public interface AnalyseInterface {
    /**
     *
     * @return all analyses known by Analysemodule
     */
    ArrayList<Analyse> getAnalysesTOMainFrame();

    /**
     *
     * @param analyses the transformations to be set active
     */
    void setAnalyses(ArrayList<Analyse> analyses);

    /**
     *
     *  true if the start was successfully
     */
    void startup();

    /**
     *
     * stops the Analyses Module
     */
    void stopit();
}
