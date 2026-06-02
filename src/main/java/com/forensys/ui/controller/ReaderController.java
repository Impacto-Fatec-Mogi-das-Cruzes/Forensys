package com.forensys.ui.controller;

import com.forensys.core.filestructure.concrete.TextFile;
import com.forensys.service.reader.CloseTextFile;
import com.forensys.service.reader.GetTextFile;
import com.forensys.service.reader.LoadFile;

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
        TextFile file = GetTextFile.execute();
        String fileContent = LoadFile.execute(file.getContent());
        
        if (fileContent != null) {
            content.setText(fileContent);
            tittle.setText(file.getMetadata().name());
        } else {
            content.setText("Nothing to see here...");
            tittle.setText("NullPointerOperation");
        }

        content.setWrapText(true);
        content.maxWidthProperty().bind(root.widthProperty().subtract(0));

        root.setOnKeyReleased(event -> {
            if (event.getCode().toString().equals("Q")) {
                CloseTextFile.execute();
            }
        });
    }
}
