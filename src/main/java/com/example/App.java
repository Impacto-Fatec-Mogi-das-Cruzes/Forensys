package com.example;

import com.example.core.setting.Settings;
import com.example.core.setting.SettingsParser;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/interfaces/terminal.fxml")
            );
            Parent root = loader.load();
    
            Settings settings = SettingsParser.getInstance().parse("config.json");
            Scene scene = new Scene(root, settings.getScreen().getWidth(), settings.getScreen().getHeight());
    
            stage.setTitle("MainTerminalInterface");
            stage.setScene(scene);
    
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/icon.png")));
    
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);

            stage.show();
        } catch (Exception e) {
            System.err.println(e);
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}