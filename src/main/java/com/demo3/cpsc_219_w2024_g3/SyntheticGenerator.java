package com.demo3.cpsc_219_w2024_g3;

import java.util.*;

public class SyntheticGenerator {

    /**
     * Determines the minimum and maximum values of a Matrix
     * @param matrix Matrix of data
     * @return double array of min/max values
     */
    public static double[] minMax(Matrix matrix) {

        ArrayList<Double> data = new ArrayList<>();
        if (matrix.getType() == Type.COLUMN) { // Construct the double array of all data values in the vector
            for (int i = 0; i < matrix.size()[0]; i++) {
                data.add(matrix.getEntry(i, 0));
            }
        } else if (matrix.getType() == Type.ROW) {
            for (int i = 0; i < matrix.size()[1]; i++) { // Construct the double array of all data values in the vector
                data.add(matrix.getEntry(0, i));
            }
        } else {
            for (double[] row : matrix.getMatrix()) { // Construct the double array of all data values in the Matrix
                for (double entry : row) {
                    data.add(entry);
                }
            }
        }
        double min = Collections.min(data); // Compute the minimum
        double max = Collections.max(data); // Compute the maximum
        return new double[]{min,max}; // Return min/max array
    }

    /**
     * Creates a vector of specified size within a range, incremented by a specified step
     * @param range Array of bounds
     * @param n Number of data points
     * @return Array of line data
     */
    public static double[] linSpace(double[] range, int n) {
        double[] vec = new double[n];
        double min = range[0];
        double max = range[1];
        double increment = (max - min) / (n - 1); // Compute the increment as the difference between the bounds over the number of points - 1
        for (int i = 0; i < n; i++) {
            vec[i] = min + i * increment; // Generate the vector from the minimum by adding the increment to each entry
        }
        return vec;
    }

    /**
     * Create synthetic dataset with gaussian noise
     * @param a Matrix of polynomial coefficients
     * @param n Number of data points
     * @param range Min/max of data
     * @param fraction Fraction of maximum allowable error
     * @return Matrix of synthetic data
     */
    @SuppressWarnings("unused")
    public static Matrix withNoise(Matrix a, int n, double[] range, double fraction) {

        Random rng = new Random();
        Matrix clean = pure(a,n,range); // Create a matrix of clean data from the parameters
        for (int i = 0; i < clean.size()[0]; i++) { // Pollute the data
            double entry = clean.getEntry(i,1); // Get the entry
            double error = entry * fraction * (rng.nextDouble() * 2 - 1); // Random error within [-fraction, fraction]
            double noisy = (entry + error); // Add the error
            clean.setEntry(i,1,noisy); // Update the entry
        }
        return clean;
    }

    /**
     * Generate a synthetic dataset within a range, given polynomial coefficients
     * @param a Matrix of coefficients
     * @param n Number of data points
     * @param range Data range
     * @return Clean synthetic data
     */
    public static Matrix pure(Matrix a, int n, double[] range) {

        int order = a.size()[0]; // Order of the polynomial + 1
        double[][] syn = new double[n][2]; // Initialize an nx2 data array
        double[] x = linSpace(range,n); // Create a vector of x values
        for (int i = 0; i < n; i++) {
            syn[i][0] = x[i]; // Populate with x values
            syn[i][1] = 0.0; // Initialize y value as 0
            for (int j = 0; j < order; j++) {
                syn[i][1] += (Math.pow(syn[i][0], j) * a.getEntry(j, 0)); // Compute and add each power of the polynomial for this entry
            }
        }
        return new Matrix(syn);
    }

}
