package src;

import processing.core.PApplet;

/**
 * Our implementation of the marching cube algorithm
 */
public class MarchingCube {

    ScalarField scalarField;
    int isoLevel;

    /**
     * @param scalarField The scalarField containing our input data
     * @param isolevel the selected isoLevel
     */
    public MarchingCube(ScalarField scalarField, int isolevel) {
        this.scalarField = scalarField;
        this.isoLevel = isolevel;
    }

    /**
     * Compute the marching cube algorithm with the parameter set inside the class
     */
    public void march() {
        for (Cube cube : scalarField) {
            int cubeIndex = 0;

            //calculate cube index to get the current cube configuration
            for (int i = 0; i < 8; i++) {
                if (cube.getPoints()[i].isoLevel > isoLevel) {
                    cubeIndex |= 1 << i;
                }
            }


            //From the current configuration, draw the triangles
            for(int i = 0; TriangulationTable.triTable[cubeIndex][i] !=-1; i += 3) {
                cube.triangleFromEdgeIndexes(
                        TriangulationTable.triTable[cubeIndex][i],
                        TriangulationTable.triTable[cubeIndex][i+1],
                        TriangulationTable.triTable[cubeIndex][i+2],
                        isoLevel)
                        .draw();
            }
        }
    }
}

