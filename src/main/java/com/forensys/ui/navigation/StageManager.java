package com.forensys.ui.navigation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

import com.forensys.core.setting.SettingsParser;
import com.forensys.core.setting.Window;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/* TODO: funções da SceneManager
- Recebe uma stage e permite mudar os atributos da stage
- Gerenciamento de Scenes: 
    - Adicionar as cenas a um registry
    - Adicionar pilhas de cenas
    - Mudar de cenas
*/
public class StageManager {

    private static StageManager instance;
    private Stage stage;
    private Deque<Scene> history = new ArrayDeque<>();

    public static void init(Stage stage) {
        if (instance != null) {
            throw new IllegalStateException("StageManager already initialized");
        }
        instance = new StageManager(stage);
    }

    public static StageManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("StageManager not initialized");
        }
        return instance;
    }

    private StageManager(Stage stage) {
        this.stage = stage;
        applySettings();
        stage.show();

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void switchScene(String key) {
        Supplier<Scene> supplier = SceneRegistry.getInstance().get(key);
        if (supplier == null) {
            throw new IllegalStateException("Scene not found");
        }
        if (stage.getScene() != null) {
            history.add(stage.getScene());
        }
        stage.setScene(supplier.get());
    }

    public void restoreScene() {
        if (!history.isEmpty()) {
            stage.setScene(history.pop());
        }
    }

    public void applySettings() {
        Window window = SettingsParser.getInstance().parse("config").getWindow();
        this.stage.setTitle(window.title());
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/" + window.icon())));
        this.stage.initStyle(window.style());
        this.stage.setResizable(window.resizable());
        this.stage.setHeight(window.height());
        this.stage.setWidth(window.width());
    }

}