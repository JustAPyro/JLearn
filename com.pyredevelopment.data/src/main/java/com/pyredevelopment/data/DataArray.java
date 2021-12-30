package com.pyredevelopment.data;

import java.util.*;
import java.util.stream.Stream;

/**
 * Array-List based DataObject
 *
 * <h3>Overview:</h3>
 * // TODO: Fill
 */
public class DataArray implements DataObject{

    // - - - - - - - - - - Instance Values - - - - - - - - - - - -

    ArrayList<String> headers;
    ArrayList<ArrayList<Object>> data;

    // - - - - - - - - - - Constructors - - - - - - - - - - - -

    public DataArray() {

        // Instantiate underlying data structures
        headers = new ArrayList<>();
        data = new ArrayList<>();
    }

    // - - - - - - - - - - Getters / Setters - - - - - - - - - - - -

    /**
     * ALlows you to set the headers of the data (Aka the feature or column titles)
     * @param headers An ArrayList of the desired headers
     */
    public void setHeaders(ArrayList<String> headers) {
        this.headers = headers;
    }

    public ArrayList<String> getHeaders() {
        return this.headers;
    }

    // - - - - - - - - - - Creator Methods - - - - - - - - - - -

    /**
     * Allows the creation of DataArrays from a hashmap object that contains
     * List implementing objects, by default this uses the Keys as column headers.
     */
    public static DataArray fromHashMap(HashMap<?, ? extends List<?>> hashMap) {

        // Creating a DataArrayOut, which we will build then return
        DataArray dataArrayOut = new DataArray();

        // For each key in the map
        for (Object columns : hashMap.keySet()) {

            // Add the key as a header
            dataArrayOut.headers.add(columns.toString());

            // And instantiate a new arrayList to hold data for this
            ArrayList<Object> featureData = new ArrayList<Object>();

            // Iterate through items in the list
            for (Object item : hashMap.get(columns)) {

                // Add it to the list
                featureData.add(item);

            }

            dataArrayOut.data.add(featureData);
        }

        // Return the results
        return dataArrayOut;
    }

    // - - - - - - - - - - Overridden Methods - - - - - - - - - -

    public String toString() {

        // Start by assuming the string size of each col is equal to the header
        int stringSize[] = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++)
            stringSize[i] = headers.get(i).length();

        // Create a formatter string
        final StringBuilder formatString = new StringBuilder("| %-2s ");
        for (int val : stringSize)
            formatString.append("| %" + "-" + val + "s ");
        formatString.append("|\n");

        /*
         * Prepare line for top, bottom & below header row.
         */
        StringBuilder templateBuilder = new StringBuilder();
        templateBuilder.append("+----+");
        for (int val : stringSize)
            templateBuilder.append("-".repeat(val+2)).append("+");
        String line = templateBuilder.toString() + "\n";

        // Create the final table
        StringBuilder finalBuilder = new StringBuilder(line);
        ArrayList<String> tempHeaders = new ArrayList<String>();
        tempHeaders.add("id");
        tempHeaders.addAll(headers);
        finalBuilder.append(String.format(formatString.toString(), tempHeaders.toArray()));
        finalBuilder.append(line);

        for (int sample = 0; sample < data.get(0).size(); sample++) {

            tempHeaders = new ArrayList<String>();
            tempHeaders.add(String.valueOf(sample));
            for (int feature = 0; feature < headers.size(); feature++) {
                tempHeaders.add(data.get(feature).get(sample).toString());
            }

            finalBuilder.append(String.format(formatString.toString(), tempHeaders.toArray()));


        }

        if (true)
            return finalBuilder.toString();


        // Create the string builder
        StringBuilder stringBuilder = new StringBuilder();

        // Format the headers
        stringBuilder.append("| i | ");
        for (String columnHead : headers)
            stringBuilder.append(columnHead).append("\t| ");

        stringBuilder.append("\n+---+");
        stringBuilder.append("-".repeat(Math.max(0, stringBuilder.length()-4)));

        return stringBuilder.toString();
    }

}
