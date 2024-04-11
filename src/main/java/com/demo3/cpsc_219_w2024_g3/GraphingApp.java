package com.demo3.cpsc_219_w2024_g3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GraphingApp extends Application {

    public GraphingApp() {} // Application

    /**
     *
     * @param stage Stage to present
     * @throws IOException in the controller
     */
    @Override @SuppressWarnings("all")
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(GraphingApp.class.getResource("regression-template.fxml")); // Create from fxml file
        loader.setController(new GraphingController()); // Assign controller
        Scene scene = new Scene(loader.load(), 550, 400); // Build the scene from the fxml file
        scene.getStylesheets().add(getClass().getResource("root.css").toExternalForm()); // Import chart stacking .css file
        stage.setTitle("Graphing Application"); // Set title
        stage.setScene(scene); // Set the scene
        stage.show(); // Show the stage
    }

    public static void main(String[] args) {
        launch(); // Launch the application
    }
}

