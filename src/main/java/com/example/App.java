package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/interfaces/MainTerminalInterface.fxml")
            );
            Parent root = loader.load();
    
            Scene scene = new Scene(root, 1024, 768);
    
            stage.setTitle("Projeto POO");
            stage.setScene(scene);
    
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/icon.png")));
    
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);

            stage.show();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}