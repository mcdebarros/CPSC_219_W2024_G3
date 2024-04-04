package com.demo3.cpsc_219_w2024_g3;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class MatrixOperatorsDemo {
    public static void main(String[] args) throws FileNotFoundException {
        String blueColor = "\u001B[34m";
        String cyanColor = "\u001B[36;1m";
        String resetColor = "\u001B[0m";

        System.out.print("""
                \nThis demo is here to show the matrix operations in action!
                \n""");
        Matrix data = new Matrix(args[0]);

        // Shows Original com.demo3.cpsc_219_w2024_g3.Matrix
        System.out.println(STR."\{cyanColor}Original com.demo3.cpsc_219_w2024_g3.Matrix:\{cyanColor}");
        data.showMat();
        System.out.println();

        // Shows all the important info
        // Determinant, Length and Width of com.demo3.cpsc_219_w2024_g3.Matrix, whether it is a square matrix or not, and the type of data inputted
        System.out.println(STR."\{cyanColor}Important Info:\{cyanColor}");
        System.out.println(STR."\{blueColor}~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\{resetColor}");
        System.out.println(STR."\{blueColor}|\{resetColor}  Determinant\t\{blueColor}|\{resetColor}\t [Length, Width] \t\{blueColor}|\{resetColor}\tSquare com.demo3.cpsc_219_w2024_g3.Matrix?\t\{blueColor}|\{resetColor}\t\tType?\t\t\{blueColor}|\{resetColor}");
        System.out.println(STR."\{blueColor}|---------------|-----------------------|-------------------|-------------------|\{resetColor}");
        System.out.print(STR."\{blueColor}|\{resetColor}\t  \{data.getDet()}\t\t");
        System.out.print(STR."\{blueColor}|\{resetColor}\t\t \{Arrays.toString(data.size())}\t\t\t");
        if (data.isSquare()) {
            System.out.print(STR."\{blueColor}|\{resetColor}\t\tTrue\t\t\{blueColor}|\{resetColor}");
        } else {
            System.out.print(STR."\{blueColor}|\{resetColor}\t\t  False\t\t\t\{blueColor}|\{resetColor}");
        }
        System.out.println(STR."\t\t\{data.getType()}\t\t\{blueColor}|\{resetColor}");
        System.out.println(STR."\{blueColor}~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\{resetColor}");
        System.out.println();

        // Shows Transposed com.demo3.cpsc_219_w2024_g3.Matrix
        System.out.println(STR."\{cyanColor}Transposed com.demo3.cpsc_219_w2024_g3.Matrix:\{cyanColor}");
        Matrix transposedData = Matrix.transpose(data);
        transposedData.showMat();
        System.out.println();

        // Shows Inverted com.demo3.cpsc_219_w2024_g3.Matrix
        System.out.println(STR."\{cyanColor}Inverse com.demo3.cpsc_219_w2024_g3.Matrix:\{cyanColor}");
        Matrix invertedData = Matrix.inverse(data);
        invertedData.showMat();
        System.out.println();

        // Shows Multiplied com.demo3.cpsc_219_w2024_g3.Matrix of Original com.demo3.cpsc_219_w2024_g3.Matrix First and Inverse com.demo3.cpsc_219_w2024_g3.Matrix
        // **Should show the Identity com.demo3.cpsc_219_w2024_g3.Matrix**
        System.out.println(STR."\{cyanColor}Multiplied com.demo3.cpsc_219_w2024_g3.Matrix of Original com.demo3.cpsc_219_w2024_g3.Matrix and Inverse com.demo3.cpsc_219_w2024_g3.Matrix:\{cyanColor}");
        Matrix multipliedMatrix = Matrix.matMult(data, invertedData);
        multipliedMatrix.showMat();

    }
}
