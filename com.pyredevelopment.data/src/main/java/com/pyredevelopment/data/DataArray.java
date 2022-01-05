package com.pyredevelopment.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static DataArray readCSV(String fileLocation, boolean includeFileHeaders, char divider) {

        // Try, in case we encounter IO exception
        try {

            // Create a buffered reader from the provided file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));

            // Create a variable to store the current line being processed
            String lineToBeParsed;

            // Create a new DataFrame for the output
            DataArray output = new DataArray();

            // If we're including fileHeaders read that line first
            if (includeFileHeaders)
                // set the headers of the output using the readCSVHeader method
                output.setHeaders(readCSVHeader(bufferedReader, divider));



            // For each line we iterate through
            while((currentLine = bufferedReader.readLine()) != null) {

                // Add this line as an instance to the output
                output.addInstance(Instance.parseCSV(currentLine, divider));

            }

            // Return the DataFrame
            return output;

        } catch (IOException e) {
            // If we do encounter an error, just print the error and return null
            e.printStackTrace();
            return null;
        }
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

    // - - - - - - - - - - - - - Private Methods - - - - - - - - - -

    private static ArrayList<String> readCSVHeader(BufferedReader reader, char deliminator) throws IOException{

        // Get the first line of the file
        char[] headerChars = reader.readLine().toCharArray();

        // Create a list of the headers
        ArrayList<String> outputHeaders = new ArrayList<>();

        // Create a buffer for each header
        StringBuilder buffer = new StringBuilder();

        // For each character
        for (char c : headerChars)
            // Process it using the process method
            processCharForHeader(c, deliminator, buffer, outputHeaders);

        // Return the results
        return outputHeaders;
    }

    /*
    This processes an individual char, if it's the deliminator character it will push
    buffer into the provided array, otherwise the char will simply be added to the buffer.
     */
    private static void processCharForHeader(char c, char deliminator, StringBuilder buffer, ArrayList<String> outputHeaders) {
        // If the character is the deliminator (usually comma)
        if (c == deliminator) {

            // Add the word to the headers and clear buffer
            outputHeaders.add(buffer.toString());
            buffer.setLength(0);

        }
        else {

            // Other-wise add the character to the buffer
            buffer.append(c);

        }
    }


}
