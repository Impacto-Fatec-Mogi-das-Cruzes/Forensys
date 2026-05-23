package com.forensys.ui.controller;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.ImageFile;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ViewController {

    @FXML
    private StackPane center;

    @FXML
    private ImageView content;

    @FXML
    private BorderPane root;

    @FXML
    private Label tittle;

    private double scale = 1.0;
    private static final double ZOOM_FACTOR = 1.1;

    @FXML
    private void initialize() {

        ImageFile file = ApplicationContext.getInstance().getImageFile();
        content.toBack();

        if (file != null) {
            Image image = new Image(
                getClass().getResourceAsStream("/assets/filestructure/" + file.getPath())
            );

            content.setImage(image);
            tittle.setText(file.getMetadata().name());
        } else {
            content.setImage(null);
            tittle.setText("No image loaded");
        }

        content.setPreserveRatio(true);

        Platform.runLater(() -> {
            root.requestFocus();
        });

        center.setFocusTraversable(true);

        root.setOnScroll(event -> {
            if (content.getImage() == null) return;

            double deltax = event.getDeltaX();
            double deltay = event.getDeltaY();

            if (event.isControlDown()) {
                if (deltay == 0) {
                    return;
                }
                zoom(deltay > 0 ? ZOOM_FACTOR : 1/ZOOM_FACTOR);
                return;
            }
            move(deltax, deltay);

            event.consume();
        });

        root.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.Q) {
                ApplicationContext.getInstance().closeImage();
            }
        });

        root.setOnKeyPressed(event -> {
            if (content.getImage() == null) return;

            if (event.getCode() == KeyCode.PLUS || event.getCode() == KeyCode.EQUALS) {
                zoom(ZOOM_FACTOR);
            }

            if (event.getCode() == KeyCode.MINUS) {
                zoom(1 / ZOOM_FACTOR);
            }

            if (event.getCode() == KeyCode.DIGIT0) {
                resetZoom();
                resetPosition();
            }

            if (event.getCode() == KeyCode.UP) {
                move(0, 25);
            }

            if (event.getCode() == KeyCode.DOWN) {
                move(0, -25);
            }

            if (event.getCode() == KeyCode.RIGHT) {
                move(-25, 0);
            }

            if (event.getCode() == KeyCode.LEFT) {
                move(25, 0);
            }
        });
    }

    private void zoom(double factor) {
        scale *= factor;

        scale = Math.max(0.1, Math.min(scale, 10.0));

        content.setScaleX(scale);
        content.setScaleY(scale);
    }

    private void resetZoom() {
        scale = 1.0;
        content.setScaleX(scale);
        content.setScaleY(scale);
    }

    private void move(double x, double y) {
        content.setTranslateY(content.getTranslateY() + y);
        content.setTranslateX(content.getTranslateX() + x);
    }

    private void resetPosition() {
        content.setTranslateX(0);
        content.setTranslateY(0);
    }
}