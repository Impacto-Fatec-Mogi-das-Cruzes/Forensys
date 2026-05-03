package com.forensys;

import com.forensys.ui.navigation.InterfaceParser;
import com.forensys.ui.navigation.SceneRegistry;
import com.forensys.ui.navigation.StageManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            registerAllScenes("terminal", "chat", "reader");
            StageManager.init(stage);
            StageManager.getInstance().switchScene("terminal");
        } catch (Exception e) {
            System.err.println(e);
            Platform.exit();
        }
    }

    private void registerAllScenes(String... resources) {
        SceneRegistry sceneRegistry = SceneRegistry.getInstance();
        for (String resource : resources) {
            sceneRegistry.register(
                    resource,
                    () -> {
                        return InterfaceParser.getInstance().parse(resource);
                    });
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}