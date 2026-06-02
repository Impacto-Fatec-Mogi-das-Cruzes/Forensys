package com.forensys.ui.controller;

import com.forensys.core.filestructure.concrete.TextFile;
import com.forensys.service.reader.CloseTextFile;
import com.forensys.service.reader.GetTextFile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class ReaderController {
    @FXML
    private TextArea content;

    @FXML
    private BorderPane root;

    @FXML
    private Label tittle;

    @FXML
    private void initialize() {
        // TODO: change content from raw text to a path to a resource
        TextFile file = GetTextFile.execute();
        setContent(file);

        content.setWrapText(true);

        content.maxWidthProperty().bind(root.widthProperty().subtract(0));


        root.setOnKeyReleased(event -> {
            if (event.getCode().toString().equals("Q")) {
                CloseTextFile.execute();
            }
        });
    }

    private void setContent(TextFile file) {
        if (file == null) {
            content.setText("Nothing to see here...");
            tittle.setText("NullPointException");
            return;
        }
        content.setText(file.getContent());
        tittle.setText(file.getMetadata().name());

    }
}
