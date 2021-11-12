package src.generator;


import src.Point;

/**
 * An generator capable of producing an isosurface value for each point
 */
public interface Generator {
    /**
     * Produce an isosurface value for each point
     * @param pts The currently processed point
     * @return The isosurface  value
     */
    int generate(Point pts);
}
