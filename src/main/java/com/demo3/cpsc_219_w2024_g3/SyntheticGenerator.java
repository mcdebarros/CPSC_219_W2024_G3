package com.demo3.cpsc_219_w2024_g3;

import java.util.*;

public class SyntheticGenerator {

    public static double[] minMax(Matrix matrix) {

        ArrayList<Double> data = new ArrayList<>();
        if (matrix.getType() == Type.COLUMN) {
            for (int i = 0; i < matrix.size()[0]; i++) {
                data.add(matrix.getEntry(i, 0));
            }
        } else if (matrix.getType() == Type.ROW) {
            for (int i = 0; i < matrix.size()[1]; i++) {
                data.add(matrix.getEntry(0, i));
            }
        } else {
            for (double[] row : matrix.getMatrix()) {
                for (double entry : row) {
                    data.add(entry);
                }
            }
        }
        double min = Collections.min(data);
        double max = Collections.max(data);
        return new double[]{min,max};
    }

    public static double[] linSpace(double[] range, int n) {
        double[] vec = new double[n];
        double min = range[0];
        double max = range[1];
        double increment = (max - min) / (n - 1);
        for (int i = 0; i < n; i++) {
            vec[i] = min + i * increment;
        }
        return vec;
    }

    public static Matrix withNoise(Matrix a, int n, double[] range, double fraction) {

        Random rng = new Random();
        Matrix clean = pure(a,n,range);
        for (int i = 0; i < clean.size()[1]; i++) {
            double entry = clean.getEntry(i,0);
            double error = clean.getEntry(i,0) * fraction * (rng.nextDouble() * 2 - 1); // Random error within [-maxErrorPercentage, maxErrorPercentage]
            double noisy = (entry + error);
            clean.setEntry(i,0,noisy);
        }
        return clean;
    }

    public static Matrix pure(Matrix a, int n, double[] range) {

        int order = a.size()[0];
        double[][] syn = new double[n][2];
        double[] x = linSpace(range,n);
        for (int i = 0; i < n; i++) {
            syn[i][0] = x[i];
            syn[i][1] = 0.0;
            for (int j = 0; j < order; j++) {
                syn[i][1] += (Math.pow(syn[i][0], j) * a.getEntry(j, 0));
            }
        }
        return new Matrix(syn);
    }

}
