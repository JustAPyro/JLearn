package com.pyredevelopment.data;

import java.util.ArrayList;
import java.util.Iterator;

public class Instance {

    // The location of the data we're keeping
    Object[] data;


    public Instance(ArrayList<String> headers) {

        // Instantiate the list to have a place for each header of the dataset
        data = new Object[headers.size()];
    }

    public void insert(Object o, int index) {
        data[index] = o;
    }

}
