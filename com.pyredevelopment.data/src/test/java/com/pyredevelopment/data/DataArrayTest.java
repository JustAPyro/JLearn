package com.pyredevelopment.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataArrayTest {

    @Test
    @DisplayName("Testing Reading from Hashmap")
    void testFromHashMap() {

        // Set up
        HashMap<String, List<Object>> hashMap = new HashMap<>();
        hashMap.put("col1", new ArrayList<>(Arrays.asList(1, 2, 10, 11, 12)));
        hashMap.put("col2", new ArrayList<>(Arrays.asList(3, 4, 13, 14, 15)));
        hashMap.put("First Name", new ArrayList<>(Arrays.asList("Luke", "John", "Michael", "Liam", "Joe")));

        // Execution
        DataArray dataArray = DataArray.fromHashMap(hashMap);

        // Validation
        assertEquals(dataArray.headers.size(), 3);


    }

    @Test
    @DisplayName("Testing the at method")
    void testAt() {

        // Set up
        HashMap<String, List<Object>> hashMap = new HashMap<>();
        hashMap.put("col1", new ArrayList<>(Arrays.asList(1, 2, 10, 11, 12)));
        hashMap.put("col2", new ArrayList<>(Arrays.asList(3, 4, 13, 14, 15)));
        DataArray dataArray = DataArray.fromHashMap(hashMap);

        int output = (int) dataArray.at(0, "col1");

        assertEquals(1, output);


    }

    @Test
    @DisplayName("Testing reading from CSV")
    void testReadCSV() {

        // Set up
        DataArray testArray;

        // Execution
        testArray = DataArray.readCSV("C:\\Users\\Luke\\Programming\\DataSets\\Kaggle\\MedicalInsuranceCosts.csv", true, ',');

        // Verify
        assertEquals(6, testArray.getHeaders().size());
    }


}