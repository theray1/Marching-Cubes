package src.generator;

import processing.core.PApplet;
import src.Point;

/**
 * Generate isosurface value for each point using processing Perlin noise.
 */
public class PerlinNoise implements Generator {
    PApplet applet;
    int spacing,nbPts;
    float step;

    /**
     *
     * @param applet link to our main processing class
     * @param spacing the spacing between each point of the scalar field
     * @param nbPts the number of points of the scalar field
     * @param  step the step between each step on the perlin noise according to processing documentation step of 0.005-0.03 work best for most application
     */
    public PerlinNoise(PApplet applet, int spacing, int nbPts, float step) {
        this.applet = applet;
        this.spacing = spacing;
        this.nbPts = nbPts;
        this.step = step;
    }

    /**
     * Produce an isosurface value for each point
     * @param pts The currently processed point
     * @return The isosurface  value
     */
    @Override
    public int generate(Point pts) {
        int maxSize = spacing *nbPts;
        //make sure we close the shape near the border
        if(pts.x==0 || pts.x==maxSize || pts.y==0 || pts.y==maxSize || pts.z ==0 || pts.z==maxSize) return 0;

        //Space between each input in the perlin noise need to be small so the output is smooth
        return Math.round(applet.noise((pts.x/ spacing)* step,(pts.y/ spacing)* step,(pts.z/ spacing)* step)* 255);
    }
}
