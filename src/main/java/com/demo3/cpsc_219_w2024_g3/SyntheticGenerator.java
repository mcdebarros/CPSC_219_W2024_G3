package com.demo3.cpsc_219_w2024_g3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

        ArrayList<Double> data = new ArrayList<>();
        for (double v : vec) {
            data.add(v);
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


}
