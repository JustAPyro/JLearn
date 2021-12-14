package com.pyredevelopment.data;

/**
 * This is the dataframe object that the machine learning algorithms take as an input.
 * Note that this is an array-based object and intended to simulate a Panda dataFrame
 */
public class DataFrame {

    // This is the object that contains all the data
    private Object[][] data;


    // - - - - - - - - - - Static Methods - - - - - - - - - - -

    // TODO: Implement reading CSV to dataframe
    public static DataFrame readCSV() {
        throw new RuntimeException("NOT IMPLEMENTED");
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
