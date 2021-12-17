package com.pyredevelopment.data;

import java.util.LinkedList;

public class Instance
{

    // - - - - - - - - - - - Instance Variables - - - - - - - - - -

    // This is the data associated with this instance
    Object[] instanceData;

    // - - - - - - - - - - Constructors - - - - - - - - - -

    /**
     * Constructs an instance with the provided inputs
     * @param inputs
     */
    public Instance(Object...inputs) {
        instanceData = inputs;
    }

    // - - - - - - - - - - Static Methods - - - - - - - - - -

    /**
     * Creates a Instance based on a CSV string value
     * @param string The CSV string (Single line) to be parsed
     * @return An instance representing that string
     */
    public static Instance parseCSV(String string, char splitChar)
    {

        // Create a temporary linked list to hold the strings
        LinkedList<String> stringObjects = new LinkedList<>();

        // Buffer to build strings into
        String stringBuffer = "";

        // For each character in the string
        for (char c : string.toCharArray()) {

            // If this is the splitting character
            if (c == splitChar) {

                // Add the string from buffer and then clear it
                stringObjects.add(stringBuffer);
                stringBuffer = "";

            }
            else {

                // Otherwise just add the character to the string
                stringBuffer += c;
            }
        }
        stringObjects.add(stringBuffer);

        // Create a new array based on the size
        Object[] allObjects = new Object[stringObjects.size()];

        // For each string object determine their correct type and add them to the new array
        for (int i = 0; i < allObjects.length; i++) {
            //TODO Remove try catch to max speed
            try {
                allObjects[i] = Double.valueOf(stringObjects.get(i));
            } catch (Exception e) {
                allObjects[i] = stringObjects.get(i);
            }
            allObjects[i] = stringObjects.get(i);
        }

        // Return the newly created list
        return new Instance(allObjects);
    }

    // ORGANIZE

    public double feature(int i) {

        return Double.parseDouble((String) instanceData[i]);
    }
}
