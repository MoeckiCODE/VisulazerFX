package TransformationTrans;

import Transformation.Transformation;

import java.util.ArrayList;

public interface TransformationInterface {
    /**
     *
     * @return all Transformations known by Transformationmodule
     */
    ArrayList<Transformation> getTransformationMainFrame();

    /**
     *
     * @param transformations the transformations to be set active
     */
    void setTransformations(ArrayList<Transformation> transformations);

    /**
     *
     * @return true if the start was successfully
     */
    boolean start();

    /**
     *
     * @return true if the stop was successfully
     */
    boolean stop();
}
