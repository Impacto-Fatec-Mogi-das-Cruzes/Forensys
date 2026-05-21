package com.forensys.ui.controller;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.ImageFile;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ViewController {
    @FXML
    private VBox center;

    @FXML
    private ImageView content;

    @FXML
    private BorderPane root;

    @FXML
    private Label tittle;

    @FXML
    private void initialize() {
        
        ImageFile file = ApplicationContext.getInstance().getImageFile();
        if (file != null) {
            Image image = new Image(getClass().getResourceAsStream("/assets/filestructure/" + file.getPath()));
            content.setImage(image);
            tittle.setText(file.getMetadata().name());
        } else {
            content.setImage(null);
            tittle.setText("NullPointException");
        }

        Platform.runLater(() -> {
            content.fitWidthProperty().bind(center.widthProperty().subtract(24));
            content.fitHeightProperty().bind(center.heightProperty().subtract(24));
            root.requestFocus();
        });
        root.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.Q) {
                ApplicationContext.getInstance().closeImage();
            }
        });
    }
}
