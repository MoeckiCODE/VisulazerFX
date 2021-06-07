import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import static java.lang.Math.random;
import java.util.ArrayList;

public class Cube {
    Integer id;
    static int boxes;
    double size;
    double halfsize;
    Scene bla;
    double deltax;
    double deltay;
    double deltaz;
    double x;
    double y;
    double z;
    double[] color = new double[3];
    double minx;
    double miny;
    double minz;
    double maxx;
    double maxz;
    double maxy;
    javafx.scene.shape.Box box;
    Group grp;

    public Cube(double s, Scene bla, Group grp, Integer id, double x, double y, double z){
        this.id = id;
        this.grp = grp;
        this.bla = bla;
        color[0] = random();
        color[1] = random();
        color[2] =  random();
        size = s;
        halfsize = size/2;
        box = new javafx.scene.shape.Box(size, size, size);
        deltax = 0;
        deltay = 0;
        deltaz = 0;
        this.x =  x;
        this.y = y;
        this.z = z;
        box.setTranslateX(x);
        box.setTranslateY(y);
        box.setTranslateZ(z);
        PhongMaterial mat = new PhongMaterial();
        mat.setSpecularColor(Color.color(color[0], color[1],  color[2]));
        mat.setDiffuseColor(Color.color(color[0], color[1],  color[2]));
        box.setMaterial(mat);
        grp.getChildren().add(box);
        minx = x-halfsize;
        miny = y-halfsize;
        minz = z-halfsize;
        maxx = x +halfsize;
        maxy = y +halfsize;
        maxz = z +halfsize;
        boxes++;





    }

    public void collide(ArrayList<Cube> boxes){
        boxes.forEach(box -> {
            if(box != this){


                if(collides(this, box)) {
                    double tmpvx = deltax;
                    double tmpvy = deltay;
                    double tmpvz = deltaz;
                    deltax = box.deltax;
                    deltay = box.deltay;
                    deltaz = box.deltaz;
                    box.deltax = tmpvx;
                    box.deltay = tmpvy;
                    box.deltaz = tmpvz;

                }
            }});



    }
    private boolean collides(Cube b1, Cube b2){
        return((b1.minx <= b2. maxx && b1.maxx >= b2.minx) &&
                (b1.miny <= b2.maxy && b1.maxy >= b2.miny) &&
                (b1.minz <= b2.maxz && b1.maxz >= b2. minz));
    }

    public void move(){
        x = x + deltax;
        y = y + deltay;
        z = z + deltaz;

        minx = x-halfsize;
        miny = y-halfsize;
        minz = z-halfsize;
        maxx = x +halfsize;
        maxy = y +halfsize;
        maxz = z +halfsize;
        box.setTranslateX(x);
        box.setTranslateY(y);
        box.setTranslateZ(z);

        if(maxx> bla.getWidth()   || minx < 1)
            deltax = deltax * -1;
        if(maxy > bla.getHeight() || miny < 1)
            deltay = deltay * -1;
        if(maxz > 10|| minz < -3000)
            deltaz = deltaz * -1;

    }
}
