package com.pyredevelopment.data;

import com.pyredevelopment.data.DataFrame;

public class DataFrameTest {

    public static void main(String[] args) {
        readCSVTest("C:\\Users\\Luke\\Downloads\\5m-Sales-Records\\5m Sales Records.csv", true, 10);
    }

    public static void readCSVTest(String file, boolean logInfo, int numberOfTests) {

        // For each test you want to run
        for (int i = 0; i < numberOfTests; i++) {

        }

        System.out.println("Testing CSV read time.");
        long beforeTime = System.nanoTime();
        DataFrame frame = DataFrame.readCSV(file);
        long runtime = (System.nanoTime()-beforeTime)/1000000;
        System.out.println("Total read time: " + runtime + " milliseconds.");
        System.out.println("Read " + frame.numInstances() + " instances from file.");

    }

}
