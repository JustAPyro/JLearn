package com.pyredevelopment.data;

import java.util.*;

/**
 * Array-List based DataObject
 *
 * <h3>Overview:</h3>
 * // TODO: Fill
 */
public class DataArray implements DataObject{

    // - - - - - - - - - - Instance Values - - - - - - - - - - - -

    ArrayList<String> headers;
    ArrayList<Type> classes;
    ArrayList<ArrayList<?>> data;

    private enum Type {
        BYTE,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        CHAR,
        STRING
    }

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
            ArrayList<Object> featureData = new ArrayList<>();


            // Iterate through items in the list
            for (Object item : hashMap.get(columns)) {

                System.out.println(item.getClass());
                // Add it to the list
                featureData.add(item);

            }

            // Add the feature set to the data
            dataArrayOut.data.add(featureData);
        }

        // Return the results
        return dataArrayOut;
    }

    // - - - - - - - - - - DataFrame Imitation Methods - - - - - - - - - -

    public Object at(int index, String column) {

        // If there is a header that contains the string provided
        if (headers.contains(column)) {

            // Get the index of the column
            int columnIndex = headers.indexOf(column);

            // Then return the desired data
            return data.get(columnIndex).get(index);
        }
        else    // If that header isn't found throw an exception
            throw new NoSuchElementException(); // TODO: Modify error thrown
    }

    /**
     * The column labels of the DataFrame.
     * <p>
     * Imitation of Pandas.DataFrame.columns() - See that documentation here:
     * <a href="https://pandas.pydata.org/docs/reference/api/pandas.DataFrame.columns.html#pandas.DataFrame.columns">Pandas.DataFrame.columns</a>
     *
     * @return
     */
    @Override
    public ArrayList<String> columns() {
        return headers;
    }

    // - - - - - - - - - - Overridden Object Methods - - - - - - - - - -

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
