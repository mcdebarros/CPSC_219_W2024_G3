package com.demo3.cpsc_219_w2024_g3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GraphingApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(GraphingApp.class.getResource("regression-template.fxml"));
        loader.setController(new GraphingController());
        Scene scene = new Scene(loader.load(), 600, 400);
        stage.setTitle("Graphing Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

