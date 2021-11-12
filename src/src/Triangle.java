package src;

/**
 * Represent a triangle
 */
public class Triangle {
    Point[] vertices;
    Main applet;


    /**
     * Create a triangle from 3 points
     * @param v1 1st point
     * @param v2 2st point
     * @param v3 3st point
     * @param applet linl
     */
    public Triangle(Point v1, Point v2, Point v3, Main applet) {
        Point[] v;
        v = new Point[3];
        v[0] = v1;
        v[1] = v2;
        v[2] = v3;
        this.vertices = v;
        this.applet = applet;
    }

    /**
     * Draw this triangle as a solid purple triangle
     */
    public void draw(){
        applet.fill(vertices[0].y,0, 255 - vertices[0].y);
        applet.beginShape();
        applet.vertex(vertices[0]);
        applet.vertex(vertices[1]);
        applet.vertex(vertices[2]);
        applet.endShape(applet.CLOSE);
        applet.noFill();
    }

    /**
     * Draw this triangle but without filling it
     */
    public void drawNoFill(){
        applet.beginShape();
        applet.vertex(vertices[0]);
        applet.vertex(vertices[1]);
        applet.vertex(vertices[2]);
        applet.endShape(applet.CLOSE);
        applet.noFill();
    }
}
