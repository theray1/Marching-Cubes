package src.generator;

import src.Point;

/**
 * Generator used for debugging purpose.
 * Set all the point isolevel to the max aka 255
 * Opposite of ConstantHigh
 * @see src.generator.ConstantLow
 */
public class ConstantHigh implements Generator{
    /**
     * Produce an isosurface value for each point
     * @param pts The currently processed point
     * @return The isosurface  value
     */
    @Override
    public int generate(Point pts) {
        return 255;
    }
}
