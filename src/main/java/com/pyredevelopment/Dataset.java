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




    /*
    This processes an individual char, if it's the deliminator character it will push
    buffer into the provided array, otherwise the char will simply be added to the buffer.
    */
    private static void processChar(char c, char deliminator, StringBuilder buffer, ArrayList<String> stringList) {

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
