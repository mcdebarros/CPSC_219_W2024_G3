package com.demo3.cpsc_219_w2024_g3;

public class SyntheticGenerator {

    public static Matrix makeSynth(Matrix a, Matrix input, int n) {

        double[] x = minMax(input.getMatrix()[0]);
        double[] y = minMax(input.getMatrix()[1]);
        double[] xSyn = linSpace(x,n);
        double[] ySyn = linSpace(y,n);
        double[][] synData = new double[n][2];
        for (int i = 0; i < n; i++) {
            synData[i][0] = xSyn[i];
            synData[i][1] = ySyn[i];
        }
        return new Matrix(synData);
    }

    public static double[] minMax(double[] vec) {

        double min = 0;
        double max = 0;
        double[] range = new double[2];

        for (double point : vec) {

            if (point > max) {
                max = point;
            }
            if (point < min) {
                min = point;
            }
        }
        range[0] = min;
        range[1] = max;
        return range;
    }

    public static double[] linSpace(double[] range, int n) {

        double[] vec = new double[n];
        double space = range[1] - range[0];
        double increment = space / n;
        vec[0] = range[0];
        vec[n - 1] = range[1];
        double prev = vec[0];
        for (int i = 1; i < (n - 1); i++) {
            prev += increment;
            vec[i] = prev;
        }

        return vec;
    }


}
