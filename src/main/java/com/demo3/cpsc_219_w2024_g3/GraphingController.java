package com.demo3.cpsc_219_w2024_g3;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

public class GraphingController {

    @FXML @SuppressWarnings("unused")
    private TextField filePathField;
    @FXML @SuppressWarnings("unused")
    private TextField modelOrderField;
    @FXML @SuppressWarnings("unused")
    private TextField writeTo;
    @FXML @SuppressWarnings("unused")
    private LineChart<Double, Double> lineChart;
    @FXML @SuppressWarnings("unused")
    private Label modLabel;
    @FXML @SuppressWarnings("unused")
    private Label coefLabel;
    @FXML @SuppressWarnings("unused")
    private Label phiLabel;
    @FXML @SuppressWarnings("unused")
    private Label rSquaredLabel;
    private List<Object> model;

    /**
     * Action of "regress" button
     * @throws FileNotFoundException when input data file not found
     */
    @SuppressWarnings("all")
    public void finalizeInputs() throws FileNotFoundException {
        String path = filePathField.getText(); // Get the path file
        String orderString = modelOrderField.getText(); // Get the order
        if (!InputChecker.goodOrder(orderString)) { // Alert the user in a pop-up if the provided order could not be parsed to integer
            Alert badInput = new Alert(Alert.AlertType.INFORMATION);
            badInput.setTitle("That didn't make sense!");
            badInput.setHeaderText(null);
            badInput.setContentText("Couldn't parse an integer from your order input. Check and try again!");
            badInput.showAndWait();
        } else if (!InputChecker.goodPath(path)) { // Alert the user in a pop-up if the provided path could not be resolved or read as a .txt file
            Alert badInput = new Alert(Alert.AlertType.INFORMATION);
            badInput.setTitle("That didn't make sense!");
            badInput.setHeaderText(null);
            badInput.setContentText("Couldn't open file from specified path. Check your file and the path, and try again!");
            badInput.showAndWait();
        } else {
            try {
                model = Regression.linear(path,orderString); // Perform linear regression
                Matrix coefficients = (Matrix) model.getFirst(); // These five lines extract model information from the returned Object List
                double phi = (double) model.get(1);
                double rsq = (double) model.get(2);
                Matrix inputData = (Matrix) model.get(3);
                Matrix syntheticData = (Matrix) model.get(4);
                plotData(inputData.getMatrix(),syntheticData.getMatrix()); // Plot the data
                StringBuilder coefficientString = new StringBuilder(); // Model coefficient string builder
                for (int i = 0; i < coefficients.size()[0]; i++){ // Add coefficients to the string
                    coefficientString.append(String.format("[%2.1e] ",coefficients.getEntry(i,0)));
                }
                coefLabel.setText(coefficientString.toString()); // Set labels for model output parameters
                coefLabel.setStyle("-fx-text-fill: red;");
                String roundedPhi = String.format("%2.1e",phi);
                phiLabel.setText(roundedPhi);
                phiLabel.setStyle("-fx-text-fill: red;");
                String roundedRSquared = String.format("%3.2e",rsq);
                rSquaredLabel.setText(roundedRSquared);
                rSquaredLabel.setStyle("-fx-text-fill: red;");
            } catch (NumberFormatException e) {
                displayError("Invalid model order", "Model order must be an integer."); // Alert the user in a pop-up if the provided order could not be parsed to integer
            } catch (FileNotFoundException e) {
                displayError("File unreadable!", "Could not locate or read the file. Check the directory and try again."); // Alert the user in a pop-up if the provided path could not be resolved or read as a .txt file
            }
        }
    }

    /**
     * Opens a file browser to select .txt file path
     */
    @SuppressWarnings("unused")
    public void getFile() {
        FileChooser get = new FileChooser();
        get.setTitle("Select Data File");
        File data = get.showOpenDialog(null);
        if (data != null && FilenameUtils.getExtension(data.getPath()).equals("txt")) {
            filePathField.setText(data.getAbsolutePath());
        }
    }

    /**
     * Plots both synthetic and input model data
     * @param input Input (noisy) data
     * @param synthetic Output (exact) data
     */
    @SuppressWarnings("unchecked")
    public void plotData(double[][] input, double[][] synthetic) {

        lineChart.getData().clear(); // Clear the chart

        XYChart.Series<Double, Double> inputDataSeries = new XYChart.Series<>(); // Create a new data series and populate it with the input data
        inputDataSeries.setName("Input Data");
        for (double[] doubles : input) {
            inputDataSeries.getData().add(new XYChart.Data<>(doubles[0], doubles[1]));
        }

        XYChart.Series<Double, Double> syntheticDataSeries = new XYChart.Series<>(); // Create a new data series and populate it with the synthetic data
        syntheticDataSeries.setName("Synthetic Data");
        for (double[] doubles : synthetic) {
            syntheticDataSeries.getData().add(new XYChart.Data<>(doubles[0], doubles[1]));
        }

        lineChart.getData().addAll(inputDataSeries, syntheticDataSeries); // Add all series to the chart
    }

    /**
     * Help message and instructions for the user
     */
    @SuppressWarnings("unused")
    public void displayHelpMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("""
            - To use the tool, create a datafile named "data.txt" and copy the filepath.
            - Your data file should contain m rows and n columns, and be populated by decimal entries separated by tabs.
            - Once a window pops up, enter the path to your datafile in the space provided, and the integer order of the model you wish to fit.
            - The program will then run the regression tool and fit coefficients for your model, as well as generate an rsq and phi.""");
        alert.showAndWait();
    }

    /**
     * Generic pop-up error window
     * @param title Nature of the error
     * @param message Error message and instructions
     */
    private void displayError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Action of "write" button
     * @throws IOException if file writing fails
     */
    @SuppressWarnings("all")
    public void writeButton() throws IOException {
        String path = writeTo.getText();
        if (model == null) { // Do not permit file writing if the user has not performed a regression
            displayError("Must regress!","No regression performed yet. Regress and try again.");
        } else {
            try { // Write the model parameters and close the application
                MatWriter.writeModel(model, path);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText(STR."Model successfully written as \{path}.");
                alert.showAndWait();
                Platform.exit();
            } catch (IOException e) { // Alert the user that writing has failed
                displayError("Uh oh!", "Trouble writing to file, check path and destination, then try again.");
            }
        }
    }
}

