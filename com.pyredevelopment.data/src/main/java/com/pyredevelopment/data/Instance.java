package com.pyredevelopment.data;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void appendFirst(Object o) {
        Object[] newData = new Object[data.length+1];
        newData[0] = 0;
        for (int i = 0; i < data.length; i++) {
            newData[i+1] = data[i];
        }
        this.data = newData;
    }

    public double[] getValues() {
        double[] values = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            values[i] = Double.parseDouble(Arrays.toString(data));
        }
        return values;
    }

    public double getValue(int index) {
        Object val = data[index];
        if (val instanceof Integer)
            return (Integer) val;
        else if (val instanceof String)
            return Double.parseDouble((String) data[index]);

        return Double.MIN_VALUE;
    }

}
