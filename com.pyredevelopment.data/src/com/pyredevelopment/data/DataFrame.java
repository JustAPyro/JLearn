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

    // TODO: Implement reading CSV to dataframe
    public static DataFrame readCSV(String fileLocation) {

        // Try, in case we encounter IO exception
        try {

            // Create a buffered reader from the provided file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));

            // Create a variable to store the current line being processed
            String currentLine;

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
