package src.generator;

import src.Point;

/**
 * Generator used for debugging purpose.
 * Generate a single plane with no thickness
 */
public class ground implements Generator {

    /**
     * Produce an isosurface value for each point
     * @param pts The currently processed point
     * @return The isosurface  value
     */
    @Override
    public int generate(Point pts) {
        if(pts.y  == 0) return 255;
        return 0;
    }
}
