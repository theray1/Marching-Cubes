package src;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Represent a cube
 */
public class Cube {
    /**
     * The points of cube, following the order in report
     */
    Point[] points;
    Main applet;

    /**
     * Create a cube from a list of point. The point have to follow the order described in the report
     * @param points The points array
     * @param applet link to the main class
     */
    public Cube(Point[] points, PApplet applet) {
        this.points = points;
        this.applet = (Main)applet;
    }

    /**
     * Draw the cube
     */
    public void draw(){
        applet.beginShape(PConstants.POINTS);
        for (int i = 0; i < 4; i++) {
            applet.line(points[i],points[i+4]);
            applet.line(points[i],points[(i+1)%4]);
            applet.line(points[i+4],points[(i+1)%4+4]);
        }
        applet.endShape(applet.CLOSE);
    }

    /**
     * Getter for the cube points
     * @return an array containing the cube points
     */
    public Point[] getPoints() {
        return points;
    }

    /**
     * From 3 edge, return a triangle
     * @param edgeA 1st edge
     * @param edgeB 2nd edge
     * @param edgeC 3nd edge
     * @param isoLevel The isoLevel used
     * @return A triangle formed from 3 points on the selected edge.
     * The points position of the point between the edge is interpolated using their respective isoLevel and the global isoLevel
     */
    public Triangle triangleFromEdgeIndexes(int edgeA, int edgeB, int edgeC, int isoLevel){

        Point cornerA1 = new Point(this.applet);
        Point cornerA2 = new Point(this.applet);

        Point cornerB1 = new Point(this.applet);
        Point cornerB2 = new Point(this.applet);

        Point cornerC1 = new Point(this.applet);
        Point cornerC2 = new Point(this.applet);

        switch(edgeA) {
            case 0:
                cornerA1 = this.getPoints()[0];
                cornerA2 = this.getPoints()[1];
                break;
            case 1:
                cornerA1 = this.getPoints()[1];
                cornerA2 = this.getPoints()[2];
                break;
            case 2:
                cornerA1 = this.getPoints()[2];
                cornerA2 = this.getPoints()[3];
                break;
            case 3:
                cornerA1 = this.getPoints()[3];
                cornerA2 = this.getPoints()[0];
                break;
            case 4:
                cornerA1 = this.getPoints()[4];
                cornerA2 = this.getPoints()[5];
                break;
            case 5:
                cornerA1 = this.getPoints()[5];
                cornerA2 = this.getPoints()[6];
                break;
            case 6:
                cornerA1 = this.getPoints()[6];
                cornerA2 = this.getPoints()[7];
                break;
            case 7:
                cornerA1 = this.getPoints()[7];
                cornerA2 = this.getPoints()[4];
                break;
            case 8:
                cornerA1 = this.getPoints()[0];
                cornerA2 = this.getPoints()[4];
                break;
            case 9:
                cornerA1 = this.getPoints()[1];
                cornerA2 = this.getPoints()[5];
                break;
            case 10:
                cornerA1 = this.getPoints()[2];
                cornerA2 = this.getPoints()[6];
                break;
            case 11:
                cornerA1 = this.getPoints()[3];
                cornerA2 = this.getPoints()[7];
                break;
            default:
        }

        switch(edgeB) {
            case 0:
                cornerB1 = this.getPoints()[0];
                cornerB2 = this.getPoints()[1];
                break;
            case 1:
                cornerB1 = this.getPoints()[1];
                cornerB2 = this.getPoints()[2];
                break;
            case 2:
                cornerB1 = this.getPoints()[2];
                cornerB2 = this.getPoints()[3];
                break;
            case 3:
                cornerB1 = this.getPoints()[3];
                cornerB2 = this.getPoints()[0];
                break;
            case 4:
                cornerB1 = this.getPoints()[4];
                cornerB2 = this.getPoints()[5];
                break;
            case 5:
                cornerB1 = this.getPoints()[5];
                cornerB2 = this.getPoints()[6];
                break;
            case 6:
                cornerB1 = this.getPoints()[6];
                cornerB2 = this.getPoints()[7];
                break;
            case 7:
                cornerB1 = this.getPoints()[7];
                cornerB2 = this.getPoints()[4];
                break;
            case 8:
                cornerB1 = this.getPoints()[0];
                cornerB2 = this.getPoints()[4];
                break;
            case 9:
                cornerB1 = this.getPoints()[1];
                cornerB2 = this.getPoints()[5];
                break;
            case 10:
                cornerB1 = this.getPoints()[2];
                cornerB2 = this.getPoints()[6];
                break;
            case 11:
                cornerB1 = this.getPoints()[3];
                cornerB2 = this.getPoints()[7];
                break;
            default:
        }

        switch(edgeC) {
            case 0:
                cornerC1 = this.getPoints()[0];
                cornerC2 = this.getPoints()[1];
                break;
            case 1:
                cornerC1 = this.getPoints()[1];
                cornerC2 = this.getPoints()[2];
                break;
            case 2:
                cornerC1 = this.getPoints()[2];
                cornerC2 = this.getPoints()[3];
                break;
            case 3:
                cornerC1 = this.getPoints()[3];
                cornerC2 = this.getPoints()[0];
                break;
            case 4:
                cornerC1 = this.getPoints()[4];
                cornerC2 = this.getPoints()[5];
                break;
            case 5:
                cornerC1 = this.getPoints()[5];
                cornerC2 = this.getPoints()[6];
                break;
            case 6:
                cornerC1 = this.getPoints()[6];
                cornerC2 = this.getPoints()[7];
                break;
            case 7:
                cornerC1 = this.getPoints()[7];
                cornerC2 = this.getPoints()[4];
                break;
            case 8:
                cornerC1 = this.getPoints()[0];
                cornerC2 = this.getPoints()[4];
                break;
            case 9:
                cornerC1 = this.getPoints()[1];
                cornerC2 = this.getPoints()[5];
                break;
            case 10:
                cornerC1 = this.getPoints()[2];
                cornerC2 = this.getPoints()[6];
                break;
            case 11:
                cornerC1 = this.getPoints()[3];
                cornerC2 = this.getPoints()[7];
                break;
            default:
        }

        //NOTE: here we can use the interpolation function or the middle of the edge as an approximation for extra blocky result
        //return new Triangle(getMiddle(cornerA1, cornerA2), getMiddle(cornerB1, cornerB2), getMiddle(cornerC1, cornerC2), this.applet);
        return new Triangle(interpolate(cornerA1, cornerA2, isoLevel), interpolate(cornerB1, cornerB2,isoLevel), interpolate(cornerC1, cornerC2,isoLevel), this.applet);

    }

    /**
     * Interpolate between 2 points using isoLevel
     * @param p1 1st point to interpolate
     * @param p2 2st point to interpolate
     * @param isoLevel The isoLevel
     * @return An interpolated point
     */
    private Point interpolate(Point p1, Point p2, int isoLevel){
        float mu = ((float)isoLevel- p1.isoLevel)/(p2.isoLevel - p1.isoLevel);
        return new Point(PApplet.lerp(p1.x,p2.x, mu ), PApplet.lerp(p1.y,p2.y,mu), PApplet.lerp(p1.z, p2.z, mu), 0, this.applet);
    }

    private Point getMiddle(Point p1, Point p2){
        return new Point((p1.x + p2.x)/2,(p1.y + p2.y)/2,(p1.z + p2.z)/2,0,applet);
    }

}
