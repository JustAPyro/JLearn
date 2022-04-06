package com.pyredevelopment.mathresources;

import com.pyredevelopment.Dataset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {


    @Test
    @DisplayName("Testing Matrix Determinants")
    void determinant() {

        Dataset testMatrix;
        double expected;
        double result;

        //

        /* - - - - - Testing 1x1 Matrix Determinant - - - - - */

        // Set up
        expected = -5.0;
        testMatrix = new Dataset(new double[][] {{5}});

        // Executing
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);

        /* - - - - - Testing 2x2 Matrix Determinant - - - - - */

        // Setup
        expected = -3.0;
        testMatrix = new Dataset(new double[][] {
                {7, 5},
                {2, 1}
        });

        // Executing
        result = Matrix.determinant(testMatrix);

        // Assert
        assertEquals(expected, result);



    }


}