package fxEngine;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
/**
 * @author  Richard Moeckel
 * COPYRIGHT this Code is free to use, to modify and to share. Just reference the original GitHubpage github.com/MoeckiCODE/VisulazerFX
 */
public class Line {
    double x1;
    double y1;
    double x2;
    double y2;
    javafx.scene.shape.Line line;
    Group grp;
    Scene bla;
    Integer id;

    /**
     *  Construktor for an line
     * @param x1 Start X
     * @param y1 Start Y
     * @param x2 End X
     * @param y2 End Y
     * @param id for the Line
     * @param bla scene where the Line should be displayed
     * @param grp group where the line gets added
     */
    public Line(double x1, double y1, double x2, double y2, Integer id, Scene bla, Group grp) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.bla = bla;
        this.grp = grp;
        this.id = id;
        line = new javafx.scene.shape.Line(x1, y1, x2, y2);
        line.setStroke(Color.WHITE);
        grp.getChildren().add(line);
    }
    /**
     *
     * @param x1 set the value for X start
     */
    public void setX1(double x1) {
        this.x1 = x1;
        line.setStartX(x1);
    }
    /**
     *
     * @param y1 set the value for Y start
     */
    public void setY1(double y1) {
        this.y1 = y1;
        line.setStartY(y1);
    }

    /**
     *
     * @param x2 set the value for X end
     */

    public void setX2(double x2) {
        this.x2 = x2;
        line.setEndX(x2);
    }

    /**
     *
     * @param y2 set the value for Y end
     */
    public void setY2(double y2) {
        this.y2 = y2;
        line.setEndY(y2);
    }


}

