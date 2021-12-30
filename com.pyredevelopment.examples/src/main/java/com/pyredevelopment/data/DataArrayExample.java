package com.pyredevelopment.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataArrayExample {



    public static void main(String[] args) {

        // Creating a data Array from a HashMap<>
        HashMap<String, List<Integer>> hashMap = new HashMap<>();
        hashMap.put("col1", new ArrayList<>(Arrays.asList(1, 2)));
        hashMap.put("col2", new ArrayList<>(Arrays.asList(3, 4)));

        // Call the .fromHashMap function
        DataArray data = DataArray.fromHashMap(hashMap);

        // Then print to see the results
        System.out.println("Example DataArray created from a HashMap: \n" + data);


    }



}
