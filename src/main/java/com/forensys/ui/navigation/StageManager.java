package com.forensys.ui.navigation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import com.forensys.common.observer.Observer;
import com.forensys.common.observer.Operation;
import com.forensys.core.context.ContextOperation;
import com.forensys.core.setting.SettingsParser;
import com.forensys.core.setting.Window;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StageManager implements Observer {

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
        if (history.isEmpty()) {
            throw new NoSuchElementException("Deque is empty no item to be removed");
        }
        stage.setScene(history.pop());
    }

    private void applySettings() {
        Window window = SettingsParser.getInstance().parse("config").getWindow();
        this.stage.setTitle(window.title());
        this.stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/" + window.icon())));
        this.stage.initStyle(window.style());
        this.stage.setResizable(window.resizable());
        this.stage.setHeight(window.height());
        this.stage.setWidth(window.width());
    }

    @Override
    public void update(Operation operation) {
        if (operation == ContextOperation.OPEN_FILE.getOperation()) {
            switchScene("reader");
        } else if (operation == ContextOperation.CLOSE_FILE.getOperation()) {
            restoreScene();
        } else if (operation == ContextOperation.OPEN_CONTACT.getOperation()) {
            switchScene("chat");
        } else if (operation == ContextOperation.CLOSE_CONTACT.getOperation()) {
            restoreScene();
        }
    }

}