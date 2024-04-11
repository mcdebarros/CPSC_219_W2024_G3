package com.demo3.cpsc_219_w2024_g3;

import java.io.File;

public class InputChecker {

    /**
     * Checks if the input file path is readable, exists, and is a file
     * @param path String of the file path
     * @return boolean of file readability
     */
    public static boolean goodPath(String path) {
        File dataFile = new File(path);
        return(dataFile.exists() && dataFile.canRead() && dataFile.isFile());
    }

    /**
     * Checks that the provided string contains an integer
     * @param order String of model order
     * @return boolean of integer parsing success
     */
    @SuppressWarnings("all")
    public static boolean goodOrder(String order) {
        int number;
        try {
            number = Integer.parseInt(order); // Attempt to parse int from order string
            return true; // True if successful
        } catch (NumberFormatException e) {
            return false; // False if unsuccessful
        }
    }
}
