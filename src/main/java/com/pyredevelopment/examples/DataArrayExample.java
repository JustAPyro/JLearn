package com.pyredevelopment.examples;

import com.pyredevelopment.data.DataArray;

import java.util.HashMap;
import java.util.List;

public class DataArrayExample {

    public static void main(String[] args) {

        HashMap<String, List<Integer>> hashMap = new HashMap<>();
        hashMap.put("One", List.of(1));
        hashMap.put("Two", List.of(2));
        hashMap.put("Three", List.of(3));
        hashMap.put("Four", List.of(4));
        hashMap.put("Five", List.of(5));

        // Execution
        DataArray dataArray = DataArray.fromMap(hashMap);
    }

}
