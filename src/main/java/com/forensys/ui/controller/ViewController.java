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

    private double lastMouseX;
    private double lastMouseY;

    @FXML
    private void initialize() {
        ImageFile file = ApplicationContext.getInstance().getImageFile();
        setContents(file);

        Platform.runLater(() -> {
            root.requestFocus();
        });

        root.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.Q) {
                ApplicationContext.getInstance().closeImage();
            }
        });
        
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

        root.setOnKeyPressed(event -> {
            if (content.getImage() == null) return;

            switch (event.getCode()) {
                case PLUS, EQUALS -> zoom(ZOOM_FACTOR);
                case MINUS -> zoom(1 / ZOOM_FACTOR);
                case UP -> move(0, 25);
                case DOWN -> move(0, -25);
                case RIGHT -> move(-25, 0);
                case LEFT -> move(25, 0);
                case DIGIT0 -> reset();
                default -> {break;}
            }
        });

        root.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                lastMouseX = event.getSceneX();
                lastMouseY = event.getSceneY();
            }
        });

        root.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {

                double dx = event.getSceneX() - lastMouseX;
                double dy = event.getSceneY() - lastMouseY;

                move(dx, dy);

                lastMouseX = event.getSceneX();
                lastMouseY = event.getSceneY();
            }
        });
    }

    private void setContents(ImageFile file) {
        if (file == null) {
            content.setImage(null);
            tittle.setText("No image loaded");
            return;
        }

        Image image = new Image(getClass().getResourceAsStream("/assets/filestructure/" + file.getPath()));
        content.setImage(image);
        tittle.setText(file.getMetadata().name());
        
        content.setPreserveRatio(true);
        center.setFocusTraversable(true);
    }

    private void reset() {
        resetZoom();
        resetPosition();
    }

    private void zoom(double factor) {
        scale *= factor;

        scale = Math.max(0.1, Math.min(scale, 10.0));

        content.setScaleX(scale);
        content.setScaleY(scale);

        clampPosition();
    }

    private void resetZoom() {
        scale = 1.0;
        content.setScaleX(scale);
        content.setScaleY(scale);
    }

    private void move(double x, double y) {
        content.setTranslateY(content.getTranslateY() + y);
        content.setTranslateX(content.getTranslateX() + x);

        clampPosition();
    }

    private void clampPosition() {
        double imageWidth = content.getBoundsInLocal().getWidth() * scale;
        double imageHeight = content.getBoundsInLocal().getHeight() * scale;

        double viewWidth = center.getWidth();
        double viewHeight = center.getHeight();

        double maxOffsetX = Math.max(0, (imageWidth - viewWidth) / 2);
        double maxOffsetY = Math.max(0, (imageHeight - viewHeight) / 2);

        double currentX = content.getTranslateX();
        double currentY = content.getTranslateY();

        content.setTranslateX(
            Math.max(-maxOffsetX, Math.min(maxOffsetX, currentX))
        );

        content.setTranslateY(
            Math.max(-maxOffsetY, Math.min(maxOffsetY, currentY))
        );
    }

    private void resetPosition() {
        content.setTranslateX(0);
        content.setTranslateY(0);
    }
}