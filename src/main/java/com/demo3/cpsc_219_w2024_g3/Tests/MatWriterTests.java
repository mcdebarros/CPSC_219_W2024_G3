package com.demo3.cpsc_219_w2024_g3.Tests;

import com.demo3.cpsc_219_w2024_g3.MatWriter;
import com.demo3.cpsc_219_w2024_g3.Matrix;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatWriterTests {

    @Test
    public void test_writeModel() { // Testing to see if the MatWriter() class can write a sample regression model to a file
        //Arrange
        Matrix matrixA = new Matrix(new double[][] {{10, 20}, {30, 40}});
        double phiValue = 1;
        double rsqValue = 2;
        List<Object> sampleModel = Arrays.asList(matrixA, phiValue, rsqValue);
        //Act
        MatWriter.writeModel(sampleModel,"coefficients.txt");
        //Assert
        assertTrue(new File("coefficients.txt").exists());
    }

    @Test
    public void test_writeMat() { // Testing to see if the MatWriter() class can write a sample matrix to a file
        //Arrange
        Matrix matrixA = new Matrix(new double[][] {{10, 20}, {30, 40}});
        //Act
        MatWriter.writeMat(matrixA,"matrixData.txt");
        //Assert
        assertTrue(new File("matrixData.txt").exists());
    }
}
