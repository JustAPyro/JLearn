package com.pyredevelopment.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest {

    @Test
    void createNoParameters() {
        // Test variables
        DataArray dataArray;    // The object created
        int[] expectedSize ;    // The expected size

        // ITEM:----- Creates a non-null collection -----:
        // Set-up
        // ---> None

        // Execution
        dataArray = new DataArray();

        // Assert
        assertNotNull(dataArray);

        // ITEM:----- Creates a object of shape 0,0 -----:
        // Set-up
        expectedSize = new int[]{0, 0};

        // Execution
        dataArray = new DataArray();

        // Assert
        assertArrayEquals(expectedSize, dataArray.shape());

    }

    @Test
    void createWithHashMap() {

    }

    @Test
    void createWithCSV() {

    }

    @Test
    void drop() {
    }

    @Test
    void dropRows() {
    }

    @Test
    void dropColumns() {
    }

    @Test
    void testDropColumns() {
    }

    @Test
    void concat() {
    }

    @Test
    void concatRows() {
    }

    @Test
    void concatColumns() {
    }

    @Test
    void shape() {
    }

    @Test
    void info() {
    }

    @Test
    void head() {
    }

    @Test
    void testHead() {
    }

    @Test
    void tail() {
    }

    @Test
    void testTail() {
    }
}