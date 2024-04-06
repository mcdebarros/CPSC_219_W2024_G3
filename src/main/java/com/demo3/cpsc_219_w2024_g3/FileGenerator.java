package com.demo3.cpsc_219_w2024_g3;

public class FileGenerator {

    public static void main(String[] args) {

        int n = 50;
        double[] range = new double[]{-10,10};
        double fraction = 0.5;
        String fileName = "generated_data.txt";
        double[][] coefArray = new double[3][1];
        coefArray[0][0] = 10;
        coefArray[1][0] = 0;
        coefArray[2][0] = 1;
        Matrix a = new Matrix(coefArray);
        Matrix data = SyntheticGenerator.withNoise(a,n,range,fraction);
        MatWriter.writeData(data,fileName);
    }
}
