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
    ArrayList<ArrayList<Object>> data;

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

    public void addInstance(ArrayList<String> instance) {
        while (data.size() < instance.size()) {
            data.add(new ArrayList<Object>());
        }

        for (int i = 0; i < instance.size(); i++) {
            data.get(i).add((Object) instance.get(i));
        }
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
                output.setHeaders(parseCSVHeader(bufferedReader, divider));


            // Create a Queue of lines to be parsed
            /* NOTE: Doing it this way, so we have the potential of parsing the lines
            using multiple threads later. */

            Queue<String> linesToParse = new LinkedList<>();

            // For each (remaining) line of the csv
            while((lineToBeParsed = bufferedReader.readLine()) != null)
                // Add this line to the lines to parse
                linesToParse.add(lineToBeParsed);

            // Then for each line, parse and insert it
            linesToParse.forEach(e -> output.addInstance(parseCSVLine(e, divider)));

            // Return the DataFrame
            return output;

        } catch (IOException e) {
            // If we do encounter an error, just print the error and return null
            e.printStackTrace();
            return null;
        }
    }

    public static DataArray readCSV(String fileLocation) {
        return readCSV(fileLocation, true, ',');
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
        for (int feature = 0; feature < headers.size(); feature++) {
            stringSize[feature] = headers.get(feature).length();
            for (int i = 0; i < data.get(feature).size(); i++) {
                stringSize[feature] = Math.max(stringSize[feature], data.get(feature).get(i).toString().length());
            }
        }

        // Create a formatter string
        String idSize = String.valueOf((int) Math.floor(Math.log10(data.get(0).size())) + 1);
        final StringBuilder formatString = new StringBuilder("| %-"+idSize+"s ");
        for (int val : stringSize)
            formatString.append("| %" + "-" + val + "s ");
        formatString.append("|\n");

        /*
         * Prepare line for top, bottom & below header row.
         */
        StringBuilder templateBuilder = new StringBuilder();
        templateBuilder.append("+-").append("-".repeat(Math.max(0, Integer.parseInt(idSize)))).append("-+");
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

    /*
    Parses CSV headers using a reader and a deliminator.
     */
    private static ArrayList<String> parseCSVHeader(BufferedReader reader, char deliminator) throws IOException{

        // Get the first line of the file
        char[] headerChars = reader.readLine().toCharArray();

        // Create a list of the headers
        ArrayList<String> outputHeaders = new ArrayList<>();

        // Create a buffer for each header
        StringBuilder buffer = new StringBuilder();

        // For each character
        for (char c : headerChars)
            // Process it using the process method
            processCSVChar(c, deliminator, buffer, outputHeaders);

        // Add the final result to the buffer
        outputHeaders.add(buffer.toString());

        // Return the results
        return outputHeaders;
    }

    private static ArrayList<String> parseCSVLine(String csvLine, char deliminator) {

        ArrayList<String> outputInstance = new ArrayList<>();

        char[] chars = csvLine.toCharArray();

        StringBuilder wordBuffer = new StringBuilder();

        // For each character in the string
        for (char c: chars)
            // Process it based on the char, the deliminator. Either add the word to output or char to word
            processCSVChar(c, deliminator, wordBuffer, outputInstance);

        // Add the final word left in the buffer
        outputInstance.add(wordBuffer.toString());

        return outputInstance;

    }

    /*
    This processes an individual char, if it's the deliminator character it will push
    buffer into the provided array, otherwise the char will simply be added to the buffer.
     */
    private static void processCSVChar(char c, char deliminator, StringBuilder buffer, ArrayList<String> stringList) {
        // If the character is the deliminator (usually comma)
        if (c == deliminator) {

            // Add the word to the headers and clear buffer
            stringList.add(buffer.toString());
            buffer.setLength(0);

        }
        else {

            // Other-wise add the character to the buffer
            buffer.append(c);

        }
    }



}
