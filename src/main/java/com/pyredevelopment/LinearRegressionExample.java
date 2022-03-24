package com.pyredevelopment;

public class LinearRegressionExample {

    public static void main(String[] args) {

        // Load data set
        Dataset insurance = Dataset.readCSV("src/main/resources/insurance.csv");
        System.out.println("Here's a sample of what our data looks like: \n" + insurance.getHead());

        // Split the insurance data set into 70% test and 30% train
        Dataset[] splitData = Dataset.splitTestTrain(70, insurance);

        Dataset xTrain = splitData[Dataset.TRAIN_INPUTS];
        Dataset yTrain = splitData[Dataset.TRAIN_OUTPUT];

        // Train model
        LinearRegression model = new LinearRegression(xTrain, yTrain);

        // Get the testing input values from our split array
        // Dataset xTest = splitData[Dataset.TEST_INPUTS];

        // Use the model to predict results
        // Dataset predictions = model.predict(xTest);

        // Finally, we get our actual test output values from the split data
        // Dataset actual = splitData[Dataset.TEST_OUTPUT];

        // Evaluate the results of the model
        // Dataset.evaluateRegression(predictions, actual);
    }


}
