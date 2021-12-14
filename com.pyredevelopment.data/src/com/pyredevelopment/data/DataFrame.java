package com.pyredevelopment.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is the dataframe object that the machine learning algorithms take as an input.
 * Note that this is an array-based object and intended to simulate a Panda dataFrame
 */
public class DataFrame {

    // This is the object that contains all the data
    private Object[][] data;


    // - - - - - - - - - - Static Methods - - - - - - - - - - -

    // - - - - - readCSV - - - - -

    /**
     * PRIMARY read_CSV function
     * This allows you to read a CSV into a DataFrame
     * @param fileLocation The location of the file you want to read
     * @param includeFileHeaders If the csv includes headers as the first line or not
     * @return DataFrame representation of the file
     */
    public static DataFrame readCSV(String fileLocation, boolean includeFileHeaders) {

        // Try, in case we encounter IO exception
        try {

            // Create a buffered reader from the provided file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));

            // Create a variable to store the current line being processed
            String currentLine;

            // If we're including fileHeaders read that line first

            // For each line we iterate through
            while((currentLine = bufferedReader.readLine()) != null) {

                // Process each line here
                System.out.println(currentLine);

            }

            return null;

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
     * @param fileLocation The location of the file you want to load
     * @return DataFrame representation of the file
     */
    public static DataFrame readCSV(String fileLocation) {
        // Just call the primary readCSV function, defaulting file headers to true
        return readCSV(fileLocation, true);
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
