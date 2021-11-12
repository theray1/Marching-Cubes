package src;

import processing.core.PApplet;
import processing.core.PVector;
import src.generator.Generator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represent a scalar field
 */
public class ScalarField implements Iterable<Cube> {

    int space;
    int nbPtsX, nbPtsY,nbPtsZ;
    ArrayList<Cube> cubes;
    PApplet applet;
    Generator generator;

    /**
     * Create a scalar field given the individual number of points on each axis
     * @param space space between point in pixel
     * @param nbPtsX Number of point on x axis
     * @param nbPtsY Number of point on y axis
     * @param nbPtsZ Number of point on z axis
     * @param generator The generator to used to generate the isoLevel on each point
     * @param applet link to main processing class
     */
    public ScalarField(int space, int nbPtsX, int nbPtsY, int nbPtsZ, Generator generator, PApplet applet) {
        this.space = space;
        this.nbPtsX = nbPtsX;
        this.nbPtsY = nbPtsY;
        this.nbPtsZ = nbPtsZ;
        this.applet = applet;
        this.generator = generator;
        generateField();
    }

    /**
     * Create a scalar field given the number of total points on each axis, assuming it is the same
     * @param space space between point in pixel
     * @param nbPts The number of points an edge our scalar field, assuming it is a square
     * @param generator The generator to used to generate the isoLevel on each point
     * @param applet link to main processing class
     */
    public ScalarField(int space, int nbPts, Generator generator, PApplet applet) {
        this.space = space;
        this.nbPtsX = nbPts;
        this.nbPtsY = nbPts;
        this.nbPtsZ = nbPts;
        this.applet = applet;
        this.generator = generator;
        generateField();
    }

    private void generateField(){

        this.cubes = new ArrayList<>();

        for (int x = 0; x < nbPtsX; x++) {
            for (int y = 0; y < nbPtsY ; y++) {
                for (int z = 0; z < nbPtsZ; z++) {

                    //see cube scheme in for reference
                    //we are defining each cube point as an translation from point (0,0,0).
                    int[][] pointsTranslation = {{0,0,1},{1,0,1},{1,0,0},{0,0,0},{0,1,1},{1,1,1},{1,1,0},{0,1,0}};
                    Point[] points = new Point[8];

                    for (int i = 0; i < 8; i++) {
                                float px = (x + (float)pointsTranslation[i][0])*space;
                                float py =  (y + (float)pointsTranslation[i][1])*space;
                                float pz = (z + (float)pointsTranslation[i][2])*space;
                                points[i] = new Point(
                                px, py, pz, generator.generate(new Point(px,py,pz,0,applet)),
                                applet);
                    }
                    cubes.add(new Cube(points,applet));
                }
            }
        }
    }

    /**
     * Draw all the cubes inside the scalar field
     * Used for debugging and demonstration purpose
     */
    public void drawCubes(){
        for (Cube cube: this) {
            cube.draw();
        }
    }

    /**
     * Draw all the points inside the surface that are upside the isolevel
     * Used for debugging and demonstration purpose
     * @param isoLevel The minimum isoLevel of the point to draw
     */
    public void drawPoints(int isoLevel){
        for (Cube cube: this) {
            for (Point point : cube.getPoints()){
                if (point.isoLevel > isoLevel) {
                    point.Draw(new PVector(0, 255, 0));
                } else {
                    point.Draw(new PVector(255,0,0));
                }
            }
        }
    }

    /**
     * Declare the cube as iterable to easily iterate on the points
     * @return An iterator on the cube points
     */
    @Override
    public Iterator<Cube> iterator() {
        return cubes.iterator();
    }
}
