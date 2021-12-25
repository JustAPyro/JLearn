package com.pyredevelopment.data;

public class DataFrameExample {



    public static void main(String[] args) {
        long timeBefore = System.nanoTime();
        DataFrame data = DataFrame.readCSV("C:\\Users\\Luke\\Downloads\\5m-Sales-Records\\5m Sales Records.csv");
        System.out.println(System.nanoTime()-timeBefore + " nano seconds to read complete file.");

    }

}
