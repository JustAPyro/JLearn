package com.pyredevelopment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatasetTest {

    @Test
    @DisplayName("Test Dataset Constructor using a 2D double array")
    void Dataset() {

        Dataset testDataset;
        double[][] input;
        int expectedRows;
        int expectedCols;

        /* - - - - - Test Square double array - - - - - */

        // Setup
        expectedRows = 3;
        expectedCols = 3;
        input = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        // Execute
        testDataset = new Dataset(input);

        // Assert
        assertNotNull(testDataset);
        assertEquals(expectedRows, testDataset.rows());
        assertEquals(expectedCols, testDataset.columns());

        /* - - - - - Test creating a rectangular dataset - - - - - */

        // Setup
        expectedCols = 3;
        expectedRows = 6;
        input = new double[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        // Execute
        testDataset = new Dataset(input);

        // Assert
        assertEquals(expectedCols, testDataset.columns());
        assertEquals(expectedRows, testDataset.rows());

        // Assert the outer indexes
        assertEquals(1, testDataset.getValue(0, 0));
        assertEquals(3, testDataset.getValue(0, 2));
        assertEquals(3, testDataset.getValue(5, 0));
        assertEquals(1, testDataset.getValue(5, 2));

        /* - - - - - Test creating an empty dataset - - - - - */

        // Setup
        expectedCols = 0;
        expectedRows = 0;
        input = new double[][]{};

        // Execute
        testDataset = new Dataset(input);

        // Assert
        assertEquals(expectedCols, testDataset.columns());
        assertEquals(expectedRows, testDataset.rows());

    }

}