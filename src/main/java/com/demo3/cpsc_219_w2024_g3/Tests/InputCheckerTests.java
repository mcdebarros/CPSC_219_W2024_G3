package com.demo3.cpsc_219_w2024_g3.Tests;

import com.demo3.cpsc_219_w2024_g3.InputChecker;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputCheckerTests {

    @Test
    public void test_InputCheckerValidPath() {
        //Arrange
        String validFilePath = "src/main/java/com/demo3/cpsc_219_w2024_g3/data.txt";
        //Act
        boolean result = InputChecker.goodPath(validFilePath);
        //Assert
        assertTrue(result);
    }

    @Test
    public void test_InputCheckerInvalidPath() {
        //Arrange
        String invalidFilePath = "src/main/java/com/demo3/cpsc_219_w2024_g3/invalidfile.txt";
        //Act
        boolean result = InputChecker.goodPath(invalidFilePath);
        //Assert
        assertFalse(result);
    }

    @Test
    public void test_InputCheckerEmptyPath() {
        //Arrange
        String emptyFilePath = "";
        //Act
        boolean result = InputChecker.goodPath(emptyFilePath);
        //Assert
        assertFalse(result);
    }

    @Test
    public void test_InputCheckerValidOrder() {
        //Arrange
        String validOrder = "1";
        //Act
        boolean result = InputChecker.goodOrder(validOrder);
        //Assert
        assertTrue(result);
    }

    @Test
    public void test_InputCheckerInvalidOrder() {
        //Arrange
        String invalidOrder = "A";
        //Act
        boolean result = InputChecker.goodOrder(invalidOrder);
        //Assert
        assertFalse(result);
    }

    @Test
    public void test_InputCheckerEmptyOrder() {
        //Arrange
        String emptyOrder = "";
        //Act
        boolean result = InputChecker.goodOrder(emptyOrder);
        //Assert
        assertFalse(result);
    }
}
