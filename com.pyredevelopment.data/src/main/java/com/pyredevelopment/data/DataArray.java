package com.pyredevelopment.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Array-List based DataObject
 *
 * <h3>Overview:</h3>
 * // TODO: Fill
 */
public class DataArray implements DataObject{

    ArrayList<String> headers;

    public DataArray() {

        // Instantiate underlying data structures
        headers = new ArrayList<>();
    }

    public void setHeaders(ArrayList<String> headers) {
        this.headers = headers;
    }

    /**
     * Allows the creation of DataArrays from a hashmap object that contains
     * List implementing objects, by default this uses the Keys as column headers.
     */
    public static DataArray fromHashMap(HashMap<?, ? extends List<?>> hashMap) {

        // Creating a DataArrayOut, which we will build then return
        DataArray dataArrayOut = new DataArray();

        // Iterate through each key in the hashmap and save them
        ArrayList<String> headers = new ArrayList<>();
        for (Object columns : hashMap.keySet())
            headers.add(columns.toString());

        // Set the results to the headers on DataArrayOut
        dataArrayOut.setHeaders(headers);

        // Return the results
        return dataArrayOut;
    }

    public String toString() {

        // Create the string builder
        StringBuilder sb = new StringBuilder();

        for (String columnHead : headers)
            sb.append("\t"+columnHead);

        return sb.toString();
    }

}
