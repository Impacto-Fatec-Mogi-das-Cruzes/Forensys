package com.investigacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("MainInterface.fxml")
        );
        Parent root = loader.load();

        Scene scene = new Scene(root, 1024, 768);

        stage.setTitle("FORENSYS v2.1.4 — Sistema de Análise Forense");
        stage.setScene(scene);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}