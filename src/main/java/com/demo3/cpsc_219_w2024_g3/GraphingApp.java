package com.demo3.cpsc_219_w2024_g3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphingApp extends Application {

    public GraphingApp() {}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphingApp.class.getResource("grapher.fxml"));
        fxmlLoader.setController(new GraphingController());
        Scene scene = new Scene(fxmlLoader.load(), 480, 240);
        stage.setTitle("Parameter input");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
