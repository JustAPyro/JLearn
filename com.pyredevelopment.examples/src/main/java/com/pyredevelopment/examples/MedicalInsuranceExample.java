package com.pyredevelopment.examples;

import com.pyredevelopment.data.DataArray;
import com.pyredevelopment.window.Plot;

public class MedicalInsuranceExample {

    public static void main(String[] args) {

        DataArray data = DataArray.readCSV("C:\\Users\\Luke\\Programming\\DataSets\\Kaggle\\MedicalInsuranceCosts.csv");

        Plot plt = new Plot();
        plt.plot(data, "bmi", "charges", "ro");
        plt.show();



    }

}
