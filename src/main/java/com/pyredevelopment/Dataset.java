package com.pyredevelopment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dataset {

    /**
     * TODO: Fill
     */
    public Dataset(String csvFilePath) {

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

        String lineToBeParsed;
    }

    // - - - - - - - - - - Private Methods - - - - - - - - - -

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
