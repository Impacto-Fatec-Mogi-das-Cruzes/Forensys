package com.forensys.ui.controller;

import com.forensys.ui.navigation.StageManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ReaderControler {
    @FXML
    private BorderPane root;

    @FXML
    private Label tittle;

    @FXML
    private void initialize() {
        root.setFocusTraversable(true);
        root.requestFocus();
        
        root.setOnKeyPressed(null);
        root.setOnKeyReleased(event -> {
            if (event.getCode().toString().equals("Q")) {
                StageManager.getInstance().restoreScene();
            }
        });
        root.setOnKeyTyped(null);
    }
}
