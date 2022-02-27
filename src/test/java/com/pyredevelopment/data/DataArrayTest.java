package com.pyredevelopment.data;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

        // Test Variables
        HashMap<String, List<Integer>> hashMap;
        DataArray dataArray;
        int[] expectedShape;
        String[] expectedHeaders;

        // ITEM:----- Returns data object of correct size from 1x5 -----:
        // Set-up
        hashMap = new HashMap<>();
        hashMap.put("One", List.of(1));
        hashMap.put("Two", List.of(2));
        hashMap.put("Three", List.of(3));
        hashMap.put("Four", List.of(4));
        hashMap.put("Five", List.of(5));
        expectedShape = new int[]{1, 5};

        // Execution
        dataArray = DataArray.fromMap(hashMap);

        // Assert
        assertArrayEquals(expectedShape, dataArray.shape());

        // ITEM:----- Returns data object of correct size from 5x1 -----:
        // Set-up
        hashMap = new HashMap<>();
        hashMap.put("All", List.of(1, 2, 3, 4, 5));
        expectedShape = new int[]{5, 1};

        // Execution
        dataArray = DataArray.fromMap(hashMap);

        // Assert
        assertArrayEquals(expectedShape, dataArray.shape());

        // ITEM:----- Correctly labels keys -----:
        // Set-up
        hashMap = new HashMap<>();
        hashMap.put("One", List.of(1));
        hashMap.put("Two", List.of(2));
        hashMap.put("Three", List.of(3));
        hashMap.put("Four", List.of(4));
        hashMap.put("Five", List.of(5));

        // Execution
        dataArray = DataArray.fromMap(hashMap);

        // Assert (Note that order isn't guaranteed, so we just check for contains)
        assertTrue(dataArray.getColumns().contains("One"));
        assertTrue(dataArray.getColumns().contains("Two"));
        assertTrue(dataArray.getColumns().contains("Three"));
        assertTrue(dataArray.getColumns().contains("Four"));
        assertTrue(dataArray.getColumns().contains("Five"));

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