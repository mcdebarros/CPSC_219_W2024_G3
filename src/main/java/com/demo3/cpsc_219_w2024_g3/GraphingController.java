package com.demo3.cpsc_219_w2024_g3;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.util.List;

public class GraphingController {

    @FXML @SuppressWarnings("unused")
    private TextField filePathField;
    @FXML @SuppressWarnings("unused")
    private TextField modelOrderField;
    @FXML @SuppressWarnings("unused")
    private Button finalizeButton;
    @FXML @SuppressWarnings("unused")
    private Button helpButton;
    @FXML @SuppressWarnings("unused")
    private LineChart<Double, Double> lineChart;
    @FXML @SuppressWarnings("unused")
    private Label modLabel;
    @FXML @SuppressWarnings("unused")
    private Label coefLabel;

    @SuppressWarnings("unused")
    public void finalizeInputs() {
        String path = filePathField.getText();
        String orderString = modelOrderField.getText();
        if (!InputChecker.goodOrder(orderString)) {
            Alert badInput = new Alert(Alert.AlertType.INFORMATION);
            badInput.setTitle("That didn't make sense!");
            badInput.setHeaderText(null);
            badInput.setContentText("Couldn't parse an integer from your order input. Check and try again!");
            badInput.showAndWait();
        } else if (!InputChecker.goodPath(path)) {
            Alert badInput = new Alert(Alert.AlertType.INFORMATION);
            badInput.setTitle("That didn't make sense!");
            badInput.setHeaderText(null);
            badInput.setContentText("Couldn't open file from specified path. Check your file and the path, and try again!");
            badInput.showAndWait();
        } else {
            try {
                List<Object> model = Regression.linear(path,orderString);
                Matrix coefficients = (Matrix) model.getFirst();
                double phi = (double) model.get(1);
                double rsq = (double) model.get(2);
                Matrix inputData = (Matrix) model.get(3);
                Matrix syntheticData = (Matrix) model.get(4);
                plotData(inputData.getMatrix(),syntheticData.getMatrix());
            } catch (NumberFormatException e) {
                displayError("Invalid model order", "Model order must be an integer.");
            } catch (FileNotFoundException e) {
                displayError("File unreadable!", "Could not locate or read the file. Check the directory and try again.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void plotData(double[][] input, double[][] synthetic) {

        lineChart.getData().clear();

        XYChart.Series<Double, Double> inputDataSeries = new XYChart.Series<>();
        inputDataSeries.setName("Input Data");
        for (double[] doubles : input) {
            inputDataSeries.getData().add(new XYChart.Data<>(doubles[0], doubles[1]));
        }

        XYChart.Series<Double, Double> syntheticDataSeries = new XYChart.Series<>();
        syntheticDataSeries.setName("Synthetic Data");
        for (double[] doubles : synthetic) {
            syntheticDataSeries.getData().add(new XYChart.Data<>(doubles[0], doubles[1]));
        }

        lineChart.getData().addAll(inputDataSeries, syntheticDataSeries);
    }

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

    private void displayError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

