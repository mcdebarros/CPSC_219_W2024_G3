package com.demo3.cpsc_219_w2024_g3;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MatReaderTests {

    @Test
    void test_containsHeaders() {

        String testString = "MOON\tSUN";
        boolean hasHeader = MatReader.containsHeaders(testString);
        assertTrue("False flag returned for hasHeader. Expected true.", hasHeader);
    }

    @Test
    void test_noHeaders() {

        String testString = "2.0\t3.0\t4.0";
        boolean hasHeader = MatReader.containsHeaders(testString);
        assertFalse(hasHeader,"True flag returned for hasHeader. Expected false.");
    }

    @Test
    void test_getStrings() throws FileNotFoundException {

        String fileName = "src/main/java/data.txt";
        ArrayList<String> dataLines = MatReader.getStrings(fileName);
        String expected = "1.0\t2.0\t3.0";
        assertEquals(expected,dataLines.getFirst(),STR."Incorrect string received. Expected \{expected}, received \{dataLines.getFirst()}");
    }

    @Test
    void test_getData() throws FileNotFoundException {

        String fileName = "src/main/java/data.txt";
        double[][] data = MatReader.getData(fileName);
        double expected = 1.0;
        assertEquals(expected,data[0][0],STR."Failed to construct double[][] array. Expected \{expected}, received \{data[0][0]}");
    }

    @Test
    void test_correctLength() throws FileNotFoundException {

        String fileName = "src/main/java/data.txt";
        String testString = "1.0\t2.0\t3.0";
        double[][] data = MatReader.getData(fileName);
        int expected = 3;
        int m = data.length;
        int n = data[0].length;
        assertEquals(expected,m);
        assertEquals(expected,n);
    }
}
