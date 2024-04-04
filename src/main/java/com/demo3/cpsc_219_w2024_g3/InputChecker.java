package com.demo3.cpsc_219_w2024_g3;

import java.io.File;

public class InputChecker {

    public boolean goodPath(String path) {
        File dataFile = new File(path);
        return(dataFile.exists() && dataFile.canRead() && dataFile.isFile());
    }

    public boolean goodOrder(String order) {
        int number;
        try {
            number = Integer.parseInt(order); // Attempt to parse int from order string
            return true; // True if successful
        } catch (NumberFormatException e) {
            return false; // False if unsuccessful
        }
    }
}
