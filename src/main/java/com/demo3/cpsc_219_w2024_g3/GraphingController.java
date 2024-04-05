package com.demo3.cpsc_219_w2024_g3;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.List;

public class GraphingController {

    @FXML
    private TextField filePathField;

    @FXML
    private TextField modelOrderField;

    @FXML
    private Button finalizeButton;

    @FXML
    private Button helpButton;

    @FXML
    private LineChart<Double, Double> lineChart;

    protected Stage stage;

    public void finalizeInputs() {
        String path = filePathField.getText();
        String orderString = modelOrderField.getText();
        int order = Integer.parseInt(orderString);
        boolean isGoodInput = (InputChecker.goodOrder(orderString) || InputChecker.goodPath(path));
        if (!isGoodInput) {
            displayError("Invalid file", "File not found or is not a valid file.");
            return;
        }

        try {
            int modelOrder = Integer.parseInt(orderString);
            List<Object> model = Regression.linear(path,orderString);
            Matrix inputCoefficients = (Matrix) model.getFirst();
            double[] outputCoefficients = inputCoefficients.getMatrix()[0];
            //double[] coefficients = (double[]) model.getFirst();
            double phi = (double) model.get(1);
            double rsq = (double) model.get(2);
            Matrix inputData = (Matrix) model.get(3);
            Matrix syntheticData = (Matrix) model.get(4);
            plotData(inputData.getMatrix(),syntheticData.getMatrix());
            stage.close();
        } catch (NumberFormatException e) {
            displayError("Invalid model order", "Model order must be an integer.");
        } catch (FileNotFoundException e) {
            displayError("File unreadable!", "Could not locate or read the file. Check the directory and try again.");
        }
    }

    @SuppressWarnings("unchecked")
    void plotData(double[][] input, double[][] synthetic) {
        // Clear existing data from lineChart
        lineChart.getData().clear();

        // Create axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create line chart
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);

        // Add input data series
        XYChart.Series<Number, Number> inputDataSeries = new XYChart.Series<>();
        inputDataSeries.setName("Input Data");
        for (int i = 0; i < input[0].length; i++) {
            inputDataSeries.getData().add(new XYChart.Data<>(input[0][i], input[1][i]));
        }

        // Add synthetic data series
        XYChart.Series<Number, Number> syntheticDataSeries = new XYChart.Series<>();
        syntheticDataSeries.setName("Synthetic Data");
        for (int i = 0; i < synthetic[0].length; i++) {
            syntheticDataSeries.getData().add(new XYChart.Data<>(synthetic[0][i], synthetic[1][i]));
        }

        // Add series to chart
        chart.getData().addAll(inputDataSeries, syntheticDataSeries);

        // Add series from chart to lineChart
        for (XYChart.Series<Number, Number> series : chart.getData()) {
            XYChart.Series<Double, Double> newSeries = new XYChart.Series<>();
            newSeries.setName(series.getName());
            for (XYChart.Data<Number, Number> data : series.getData()) {
                newSeries.getData().add(new XYChart.Data<>(data.getXValue().doubleValue(), data.getYValue().doubleValue()));
            }
            lineChart.getData().add(newSeries);
        }
    }


    public void displayHelpMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText(null);
        alert.setContentText("""
            - To use the tool, create a datafile named "data.txt" and copy the filepath.
            - Your data file should contain m rows and n columns, and be populated by decimal entries separated by tabs.
            - Once prompted, tell the program the path to your datafile, and the integer order of the model you wish to fit.
            - The program will then run the regression tool and fit coefficients for your model, as well as generate an rsq and phi.
            - You will have the option to write this data to an output file once the regression is complete.""");
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

