package com.forensys;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.context.ContextOperation;
import com.forensys.core.filestructure.FileStructureParser;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.Directory;
import com.forensys.core.navigation.InterfaceParser;
import com.forensys.core.navigation.SceneRegistry;
import com.forensys.core.navigation.StageManager;

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
            ApplicationContext.getInstance().subscribe(StageManager.getInstance(), 
                ContextOperation.OPEN_FILE.getOperation(), 
                ContextOperation.CLOSE_FILE.getOperation(), 
                ContextOperation.OPEN_CONTACT.getOperation(), 
                ContextOperation.CLOSE_CONTACT.getOperation() 
            );
            
            registerAllScenes("terminal", "chat", "reader");
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
}