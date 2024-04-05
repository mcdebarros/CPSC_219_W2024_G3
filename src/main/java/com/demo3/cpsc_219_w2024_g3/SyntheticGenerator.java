package com.demo3.cpsc_219_w2024_g3;

import java.util.Arrays;

public class SyntheticGenerator {

    public static Matrix makeSynth(Matrix a, Matrix input, int n) {

        double[] xCol = new double[a.size()[0]];
        for (int i = 0; i < a.size()[0]; i++) {
            xCol[i] = a.getEntry(i,0);
        }
        double[] x = minMax(xCol);
        double[] xSyn = linSpace(x,n);
        double[][] synData = new double[n][2];
        for (int i = 0; i < n; i++) {
            synData[i][0] = xSyn[i];
            synData[i][1] = a.getEntry(0,0) + (xSyn[i] * a.getEntry(1,0)) + (Math.pow(xSyn[i],2) * a.getEntry(2,0));
        }
        return new Matrix(synData);
    }

    public static double[] minMax(double[] vec) {

        double min = vec[0];
        double max = vec[0];
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
            vec[i] = prev + increment;
            prev += increment;
        }

        return vec;
    }


}
