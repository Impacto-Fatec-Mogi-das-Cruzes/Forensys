package com.forensys.ui.controller;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.TextFile;
import com.forensys.ui.navigation.StageManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class ReaderControler {
    @FXML
    private TextArea content;

    @FXML
    private BorderPane root;

    @FXML
    private Label tittle;

    @FXML
    private void initialize() {
        TextFile file = ApplicationContext.getInstance().getTextFile();
        content.setText(file.getContent());
        tittle.setText(file.getMetadata().name() + ".txt");

        root.setOnKeyReleased(event -> {
            if (event.getCode().toString().equals("Q")) {
                StageManager.getInstance().restoreScene();
            }
        });
    }
}
