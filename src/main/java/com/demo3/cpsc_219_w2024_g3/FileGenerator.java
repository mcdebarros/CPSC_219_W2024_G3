package com.demo3.cpsc_219_w2024_g3;

/**
 * This runnable utility class exists only for demonstration purposes. Its purpose is to create synthetic data with error to regress from.
 */
public class FileGenerator {

    public static void main(String[] args) {

        int n = 450; // Number of data points
        double[] range = new double[]{-50,50}; // Range of data in x
        double fraction = .3; // Fraction of error in data (1 = 100% error)
        String fileName = "generated_data.txt"; // Filename to save data
        double[][] coefArray = new double[4][1]; // Coefficients of polynomial
        coefArray[0][0] = 1;
        coefArray[1][0] = 1;
        coefArray[2][0] = 1;
        coefArray[3][0] = 1;
        Matrix a = new Matrix(coefArray); // Matrix of coefficients
        Matrix data = SyntheticGenerator.withNoise(a,n,range,fraction); // Create dataset with noise
        MatWriter.writeData(data,fileName); // Write the datafile
    }
}
