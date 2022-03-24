package com.pyredevelopment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Dataset {

    // This list contains all headers and is an ordered list
    private ArrayList<String> headers = new ArrayList<>();

    // This map contains all the columns sorted by header name keys
    HashMap<String, ArrayList<Double>> data = new HashMap<>();

    /**
     * TODO: Fill
     */
    public Dataset(String csvFilePath, char delimiter, boolean includeFileHeaders) {

        // Create a buffered reader to read in the lines of the CSV
        BufferedReader reader;

        try {

            // Try to create the reader using the csv file path
            reader = new BufferedReader(new FileReader(csvFilePath));

        }
        catch (FileNotFoundException e) {

            // If we couldn't find the file, print the error and return empty object.
            e.printStackTrace();
            return;

        }

        // This string holds the line we're planning on parse
        String lineToBeParsed;

        // Create a Queue of lines to be parsed
        /* NOTE: Doing it this way, so we have the potential of parsing the lines
        using multiple threads later. */
        Queue<String> linesToParse = new LinkedList<>();

        try {
            // If we're including fileHeaders read that line first
            if (includeFileHeaders)
                // set the headers of the output using the readCSVHeader method
                setHeaders(parseDelimitedLine(reader.readLine(), delimiter));

            // For each (remaining) line of the csv
            while((lineToBeParsed = reader.readLine()) != null)

                // Add this line to the lines to parse
                linesToParse.add(lineToBeParsed);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // For each line of the csv
        for (int i = 0; i < linesToParse.size(); i++) {

            // Parse the line
            ArrayList<String> parsedLine = parseDelimitedLine(linesToParse.poll(), delimiter);

            // For each value in this line, try to cast to a double and insert into numerical values
            try {
                for (int n = 0; n < parsedLine.size(); n++) {

                    // Convert to a Double
                    Double doubleValue = Double.valueOf(parsedLine.get(n));

                    // Add it to the appropriate column
                    getColumn(n).add(doubleValue);

                }
            }
            // If for some reason part of it can't be cast to double we deal with it here
            catch (NumberFormatException e) {
                // TODO: Add logging / error handling here
                e.printStackTrace();
            }


        }




    }

    // - - - - - - - - - - Getters/Setters - - - - - - - - - -

    public void setHeaders(ArrayList<String> headers) {

        // Set the list of headers
        this.headers = headers;

        // For each header insert into map as a key and instantiate a new column list
        for (String header : headers) {
            data.put(header, new ArrayList<Double>());
        }
    }

    public ArrayList<Double> getColumn(String string) {
        return data.get(string);
    }

    public ArrayList<Double> getColumn(int i) {
        return data.get(headers.get(i));
    }

    public String getHead() {
        return stringify(0, 5);
    }

    // - - - - - - - - - - Overridden Object Methods - - - - - - - - - -

    public String toString() {

        // Stringify data from 0 to the maximum size (all of it)
        return stringify(0, getColumn(0).size());

    }

    // - - - - - - - - - - Static Methods - - - - - - - - - -

    public static Dataset[] splitTestTrain(int percent, Dataset inputData) {
       return new Dataset[4];
    }

    public static Dataset readCSV(String filepath) {
        return new Dataset(filepath, ',', true);
    }

    // - - - - - - - - - - Private Methods - - - - - - - - - -

    private String stringify(int start, int end) {

        //This array contains the size that each column has to be
        int[] stringSize = new int[headers.size()];

        // For each feature/column in the data
        for (int feature = 0; feature < headers.size(); feature++) {

            // Start by assuming that the largest entry is the same as the header string
            stringSize[feature] = headers.get(feature).length();

            // Then for each
            for (int i = start; i < end; i++) {
                stringSize[feature] = Math.max(stringSize[feature], getColumn(feature).get(i).toString().length());
            }
        }

        // Create a formatter string
        String idSize = String.valueOf(Math.max((int) Math.floor(Math.log10(getColumn(0).size())) + 1, 2));
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
        ArrayList<String> tempHeaders = new ArrayList<>();
        tempHeaders.add("id");
        tempHeaders.addAll(headers);
        finalBuilder.append(String.format(formatString.toString(), tempHeaders.toArray()));
        finalBuilder.append(line);

        for (int sample = start; sample < end; sample++) {

            tempHeaders = new ArrayList<>();
            tempHeaders.add(String.valueOf(sample));
            for (int feature = 0; feature < headers.size(); feature++) {
                tempHeaders.add(getColumn(feature).get(sample).toString());
            }

            finalBuilder.append(String.format(formatString.toString(), tempHeaders.toArray()));

        }

        return finalBuilder.toString();


        // Create the string builder
    }

    /*
    This method is here to parse lines (mostly for CSV's) on a delimiter.
    It returns an arraylist of strings for each word between the delimiters.
     */
    private static ArrayList<String> parseDelimitedLine(String csvLine, char delimiter) {

        // Create an array of the delimited strings
        ArrayList<String> outputInstance = new ArrayList<>();

        // Split the string into characters
        char[] chars = csvLine.toCharArray();

        // Create a buffer to build specific words
        StringBuilder wordBuffer = new StringBuilder();

        // For each character in the string
        for (char c: chars)
            // Process it based on the char, the deliminator. Either add the word to output or char to word
            processChar(c, delimiter, wordBuffer, outputInstance);

        // Add the final word left in the buffer
        outputInstance.add(wordBuffer.toString());

        // Return the list of words
        return outputInstance;

    }

    /*
    This processes an individual char, if it's the deliminator character it will push
    buffer into the provided array, otherwise the char will simply be added to the buffer.
    */
    private static void processChar(char c, char delimiter, StringBuilder buffer, ArrayList<String> stringList) {

        // If the character is the deliminator (usually comma)
        if (c == delimiter) {

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
