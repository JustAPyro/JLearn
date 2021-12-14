package com.pyredevelopment.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This is the dataframe object that the machine learning algorithms take as an input.
 * Note that this is an LinkedList-based object and intended to simulate a Panda dataFrame
 */
public class DataFrame {

    // - - - - - - - - - - Instance Values - - - - - - - - - -

    // This saves the headers of the file
    private String[] headers;

    // Indicates the number of features
    private int features;

    // This is the object that contains all the data
    private LinkedList<Instance> data;


    // = = = = = = = = = = Instance Methods = = = = = = = = = =



    // - - - - - - - - - - Public Methods - - - - - - - - - -

    /**
     * Allows you to set the headers on a DataFrame
     * @param headers The headers you want set on the DataFrame
     * @throws RuntimeException If number of headers != number of features
     */
    public void setHeaders(String[] headers) {

        // Make sure the number of headers they provided are the same as number of features
        if (this.headers != null && headers.length != features)
            throw new RuntimeException("Number of headers provided doesn't equal number of features found!");

        this.headers = headers;

    }

    /**
     * Prints the header of this DataFrame
     */
    public void printHeaders() {
        // For each header print, seperated by tab
        for (String s : headers)
            System.out.print(s + "\t");
    }


    // = = = = = = = = = = Static Methods = = = = = = = = = = =

    // - - - - - - - - - - Public Static Methods - - - - - - - - - -

    // - - - - - readCSV - - - - -

    /**
     * PRIMARY read_CSV function
     * This allows you to read a CSV into a DataFrame
     * @param fileLocation The location of the file you want to read
     * @param includeFileHeaders If the csv includes headers as the first line or not
     * @return DataFrame representation of the file
     */
    public static DataFrame readCSV(String fileLocation, boolean includeFileHeaders, char divider) {

        // Try, in case we encounter IO exception
        try {

            // Create a buffered reader from the provided file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));

            // Create a variable to store the current line being processed
            String currentLine;

            DataFrame output = new DataFrame();

            // If we're including fileHeaders read that line first
            if (includeFileHeaders) {

                // Get the first line of the file
                String headerLine = bufferedReader.readLine();

                // Create a list of the headers
                LinkedList<String> headers = new LinkedList<>();

                // And a buffer for each header we might add
                String buffer = "";

                // For each character
                for (char c : headerLine.toCharArray()) {

                    // If the character is the divider (usually comma)
                    if (c == divider) {

                        // Add the word to the headers
                        headers.add(buffer);

                    }
                    else {

                        // Other-wise add the character to the buffer
                        buffer += c;

                    }
                }

                // Convert the list to an array and pass it to setHeaders
                output.setHeaders(headers.toArray(new String[0]));
            }

            // For each line we iterate through
            while((currentLine = bufferedReader.readLine()) != null) {

                // Process each line here
                System.out.println(currentLine);

            }

            // Return the DataFrame
            return output;

        } catch (IOException e) {
            // If we do encounter an error, just print the error and return null
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This allows you to read a CSV into a DataFrame
     * Overloaded to allow you to call with just fileLocation by defaulting others
     * Defaults:
     * includeFileHeaders = true
     * divider = , [comma]
     * @param fileLocation The location of the file you want to load
     * @return DataFrame representation of the file
     */
    public static DataFrame readCSV(String fileLocation) {
        // Just call the primary readCSV function, defaulting file headers to true
        return readCSV(fileLocation, true, ',');
    }

    // TODO: Implement reading ARFF to dataframe
    public static DataFrame readARFF() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    // TODO: Implement reading XML to dataframe
    public static DataFrame readXML() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    // TODO: Implement reading Excel files to dataframe
    public static DataFrame readExcel() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }

}
