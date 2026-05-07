package com.forensys;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.ContextOperation;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.Directory;
import com.forensys.ui.filestructure.FileStructureParser;
import com.forensys.ui.navigation.InterfaceParser;
import com.forensys.ui.navigation.SceneRegistry;
import com.forensys.ui.navigation.StageManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FileSystemEntry root = FileStructureParser.getInstance().parse("filestructure");
            
            ApplicationContext.init((Directory) root);
            StageManager.init(stage);
            
            ApplicationContext.getInstance().subscribe(ContextOperation.OPEN_FILE.getOperation(), StageManager.getInstance());
            ApplicationContext.getInstance().subscribe(ContextOperation.CLOSE_FILE.getOperation(), StageManager.getInstance());
            
            registerAllScenes("terminal", "chat", "reader");
            StageManager.getInstance().switchScene("chat");
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
}