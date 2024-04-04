package com.demo3.cpsc_219_w2024_g3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GraphingController {

    double[][] dataArray;
    Matrix dataMatrix;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField orderField;
    @FXML
    private TextField pathField;
    @FXML
    private GridPane outputGrid;

    public GraphingController() {

    }

    @FXML
    protected void onButtonClick() {

        String orderString = orderField.getText();
        String path = pathField.getText();
        boolean goodInput;
        statusLabel.setText("");
        while (!)


    }
}
