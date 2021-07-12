package fxEngine;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Line {
    double x1;
    double y1;
    double x2;
    double y2;
    javafx.scene.shape.Line line;
    Group grp;
    Scene bla;

    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        line = new javafx.scene.shape.Line(x1, y1, x2, y2);
        line.setStroke(Color.WHITE);
        grp.getChildren().add(line);
    }

    public void setX1(double x1) {
        this.x1 = x1;
        line.setStartX(x1);
    }

    public void setY1(double y1) {
        this.y1 = y1;
        line.setStartY(y1);
    }



    public void setX2(double x2) {
        this.x2 = x2;
        line.setEndX(x2);
    }

    public void setY2(double y2) {
        this.y2 = y2;
        line.setEndY(y2);
    }


}

