package com.example.ui.controllers;

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
                // TODO add stage stack and go back a screen
                throw new UnsupportedOperationException("Unimplemented feature quit");
            }
        });
        root.setOnKeyTyped(null);
    }
}
