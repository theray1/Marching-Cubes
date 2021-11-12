package src.generator;

import processing.core.PApplet;
import src.Point;

/**
 * Generate isosurface to draw a terrain using perlin noise.
 */
public class TerrainGenerator implements Generator {
    PApplet applet;
    int spacing,nbPts;
    float step;
    final int isoLevelMax = 255;

    /**
     *
     * @param applet link to our main processing class
     * @param spacing the spacing between each point of the scalar field
     * @param nbPts the number of points of the scalar field
     * @param  step the step between each step on the perlin noise according to processing documentation step of 0.005-0.03 work best for most application
     */
    public TerrainGenerator(PApplet applet, int spacing, int nbPts, float step) {
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

        //The terrain need to be less dense the higher we get, se we subtract the height to the isosurface after normalizing it.
        float yIncrement = -((pts.y)/spacing/nbPts * isoLevelMax);

        //since we use the coordinate between point to generate noise, we need to divide by the spacing to make sure the scale of the scalar field does not influence the output
        return  Math.round( yIncrement + applet.noise((pts.x/ spacing)* step,(pts.y/ spacing)* step,(pts.z/ spacing)* step)* isoLevelMax);
    }
}
