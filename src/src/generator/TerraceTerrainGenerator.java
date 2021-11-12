package src.generator;

import processing.core.PApplet;
import src.Point;

/**
 * Generate a terraced noise.
 * @deprecated Not working
 */
public class TerraceTerrainGenerator implements Generator {
    PApplet applet;
    int spacing,nbPts;
    float scale;
    int terraceHeight;
    final int isoLevelMax = 255;

    /**
     *
     * @param applet link to our main processing class
     * @param spacing the spacing between each point of the scalar field
     * @param nbPts the number of points of the scalar field
     * @param scale the step between each step on the perlin noise according to processing documentation step of 0.005-0.03 work best for most application
     * @param terraceHeight The desired terrace height in pixel
     */
    public TerraceTerrainGenerator(PApplet applet, int spacing, int nbPts, float scale, int terraceHeight) {
        this.applet = applet;
        this.spacing = spacing;
        this.nbPts = nbPts;
        this.scale =scale;
        this.terraceHeight = terraceHeight;
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

        //The terrain need to be less dense the higher we get, so we subtract the height to the isosurface making sure it is in [0,1].
        float yIncrement = ((pts.y)/spacing/nbPts * isoLevelMax);

        //since we use the coordinate between point to generate noise, we need to divide by the spacing to make sure the scale of the scalar field does not influence the output
        float noise =  applet.noise((pts.x/ spacing)*scale,(pts.y/ spacing)*scale,(pts.z/ spacing)*scale)* isoLevelMax;

        //the trick is to add the y position modulo the desired height
        return Math.round(-yIncrement + noise + ((pts.y)/spacing/nbPts * isoLevelMax % terraceHeight));
    }
}
