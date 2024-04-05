package com.demo3.cpsc_219_w2024_g3.Tests;

import com.demo3.cpsc_219_w2024_g3.SyntheticGenerator;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class SyntheticGeneratorTests {

    @Test
    public void test_minMax1() {
        //Arrange
        double[] testMatrix = {2, 3, -1, 3, 6};
        double[] expectedMatrix = {-1, 6};
        //Act
        double[] resultMatrix = SyntheticGenerator.minMax(testMatrix);
        //Assert
        assertArrayEquals(expectedMatrix, resultMatrix, 0.001);
    }

    @Test
    public void test_minMax2() {
        //Arrange
        double[] testMatrix = {2.0, 3.0, -1.0, 3.0, 6.0};
        double[] expectedMatrix = {-1.0, 6.0};
        //Act
        double[] resultMatrix = SyntheticGenerator.minMax(testMatrix);
        //Assert
        assertArrayEquals(expectedMatrix, resultMatrix, 0.001);
    }

    @Test
    public void test_linSpace1() {
        //Arrange
        double[] testRange = {2.0, 3.0};
        int n = 5;
        double[] expectedMatrix = {2.0, 2.2, 2.4, 2.6, 3.0};
        //Act
        double[] resultMatrix = SyntheticGenerator.linSpace(testRange, n);
        //Assert
        assertArrayEquals(expectedMatrix, resultMatrix, 0.001);
    }

    @Test
    public void test_linSpace2() {
        //Arrange
        double[] testRange = {-2.0, -1.0};
        int n = 5;
        double[] expectedMatrix = {-2.0, -1.8, -1.6, -1.4, -1.0};
        //Act
        double[] resultMatrix = SyntheticGenerator.linSpace(testRange, n);
        //Assert
        assertArrayEquals(expectedMatrix, resultMatrix, 0.001);
    }


}
