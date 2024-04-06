package com.demo3.cpsc_219_w2024_g3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.StringTemplate.STR;

public class Regression {

    protected static List<Object> linear(String path, String orderString) throws FileNotFoundException {

        int order = Integer.parseInt(orderString); // Parse the integer from the input string
        Matrix data = new Matrix(path); // Initialize the data matrix object
        List<Object> model;
        Matrix z = new Matrix(data.size()[0],order + 1); //Initialize the z array (parameters to multiply resultant coefficients by)
        Matrix y = new Matrix(data.size()[0],1); //Initialize the y vector (actual data)
        Matrix aveVec = new Matrix(data.size()[0],1); //Initialize "data mean" vector; populated entirely by mean of actual data
        double dataAve = 0.0; //Initialize data mean
        model = new ArrayList<>(); //Initialize object list to be populated with model outputs
        for (int i = 0; i < data.size()[0]; i++) { //Populate the y vector with provided data
            y.setEntry(i,0,data.getEntry(i,1));

        }
        for (int i = 0; i < data.size()[0]; i++) { //Populate the z matrix with appropriate parameters
            for (int j = 0; j < order; j++) {
                z.setEntry(i,order - j, Math.pow(data.getEntry(i,0),(order - j)));
            }
        }
        for (int i = 0; i < data.size()[0]; i++) { //Populate the first column of the z array with ones; above method does not seem to be able to do this and instead populates w/ null
            z.setEntry(i,0,1.0);
        }
        Matrix zT = Matrix.transpose(z); //Create the transpose of the z matrix
        Matrix zTz = Matrix.matMult(zT,z); //Multiply the z matrix by its transpose
        Matrix zTy = Matrix.matMult(zT,y); //Multiply the zT matrix by the y vector
        Matrix zTzInv = Matrix.inverse(zTz); //Invert the zTz matrix
        Matrix a = Matrix.matMult(zTzInv,zTy); //Calculate the vector of model coefficients
        Matrix modelOut = Matrix.matMult(z,a); //Calculate model output based on fit coefficients
        Matrix model_e = new Matrix(data.size()[0],1); //Initialize vector of model residuals
        Matrix data_e = new Matrix(data.size()[0],1); //Initialize vector of data residuals
        double phiMod = 0.0; //Initialize sum of squared residuals for model
        double phiData = 0.0; //Initialize sum of squared residuals for model
        for (int i = 0; i < data.size()[0]; i++) {
            dataAve += y.getEntry(i,0); //Sum all y values contained in the data
        }
        dataAve = dataAve / data.size()[0]; //Calculate the mean of all data y values
        for (int i = 0; i < data.size()[0]; i++) {
            aveVec.setEntry(i,0, dataAve);
            data_e.setEntry(i,0, y.getEntry(i,0) - aveVec.getEntry(i,0)); //Calculate and record the residual between the data average and the y data
            model_e.setEntry(i,0,y.getEntry(i,0) - modelOut.getEntry(i,0));//Calculate and record the residual between the modelled data and the input data
            double squareMod = Math.pow(model_e.getEntry(i,0),2); //Square the model residual
            double squareData = Math.pow(data_e.getEntry(i,0),2); //Square the data residual
            phiMod += squareMod; //Increment the sum of squared residuals
            phiData += squareData; //Increment the sum of squared residuals
        }
        double rsq = (phiData - phiMod) / phiData; //Calculate the R squared value of the produced model
        Matrix synthetic = SyntheticGenerator.pure(a,25,SyntheticGenerator.minMax(data.getCol(1)));
        model.add(a); //Populate the model list with model output
        model.add(phiMod); //Populate the model list with model output
        model.add(rsq); //Populate the model list with model output
        model.add(data);
        model.add(synthetic);


        return model; //Return the model output list
    }

    private static void printResults(List<Object> input) {
        String redColor = "\u001B[31m";
        String resetColor = "\u001B[0m";
        String TOP_BORDER = STR."\{redColor}╔═══RESULTS═══╗\{resetColor}";
        String MID_BORDER = STR."\{redColor}╠═════════════╣\{resetColor}";
        String BOTTOM_BORDER = STR."\{redColor}╚═════════════╝\{resetColor}";
        int borderLength = TOP_BORDER.length();

        Matrix a = (Matrix) input.getFirst(); // Fetch and cast the coefficient matrix object
        double phi = (double) input.get(1); // Fetch and cast the phi double
        double rsq = (double) input.get(2); // Fetch and cast the rsq double

        System.out.println(TOP_BORDER); //Forms the border top.
        for (int i = 0; i < a.size()[0]; i++) { // Prints alpha values.
            if (a.getEntry(i,0) >= 0) {
                System.out.printf(STR."\{redColor}║\{resetColor} A\{i}: %2.1e \{redColor}║\{resetColor}\n",a.getEntry(i,0));
            } else System.out.printf(STR."\{redColor}║\{resetColor} A\{i}:%2.1e \{redColor}║\{resetColor}\n",a.getEntry(i,0));
        }

        System.out.println(MID_BORDER);

        System.out.println(STR."\{redColor}║\{resetColor}     PHI     \{redColor}║\{resetColor}");
        System.out.printf(STR."\{redColor}║\{resetColor}   %2.1e   \{redColor}║\{resetColor}\n", phi);
        System.out.println(MID_BORDER);

        System.out.println(STR."\{redColor}║\{resetColor}     RSQ     \{redColor}║\{resetColor}");
        System.out.println(autoSpacer("DEFAULT",rsq,borderLength,3));

        System.out.println(BOTTOM_BORDER);
    }
    private static String autoSpacer(String start, double input, int width, int round) {
        //Initialization
        double inputRounded = Math.floor(input*(Math.pow(10,round)))/(Math.pow(10,round));
        String redColor = "\u001B[31m";
        String resetColor = "\u001B[0m";
        StringBuilder sb = new StringBuilder();
        String WALL = STR."\{redColor}║\{resetColor}";
        String SPACE = " ";
        String DEFAULT = STR."\{WALL}    ";
        int freeSpaces;
        //Calculates the amount of necessary spaces...
        int inputLength = String.valueOf(inputRounded).length();
        if (start.equals("DEFAULT")) {
            freeSpaces = (width - DEFAULT.length() - 1) - inputLength;
        } else {
            freeSpaces = (width - start.length() - 1) - inputLength;
        }
        //Build the string.
        if (start.equals("DEFAULT")) {
            if (freeSpaces % 2 == 0) {
                sb.append(DEFAULT);
                sb.append(inputRounded);
                sb.append(SPACE.repeat(freeSpaces));
            } else {
                sb.append(DEFAULT);
                sb.append(inputRounded);
                sb.append(SPACE.repeat(freeSpaces));
            }
        } else {
            sb.append(start);
            sb.append(inputRounded);
            sb.append(SPACE.repeat(freeSpaces));
        }


        sb.append(WALL);

        //Assign it to an output, and return it.
        return sb.toString();
    }
}
