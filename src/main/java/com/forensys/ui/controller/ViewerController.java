package com.forensys.ui.controller;

import com.forensys.core.filestructure.concrete.ImageFile;
import com.forensys.service.viewer.CloseImageFile;
import com.forensys.service.viewer.GetImageFile;
import com.forensys.service.viewer.LoadImage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ViewerController {

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
        content.fitWidthProperty().bind(center.widthProperty());
        content.fitHeightProperty().bind(center.heightProperty());
        content.setManaged(false);

        ImageFile file = GetImageFile.execute();
        setContent(file);

        Platform.runLater(() -> root.requestFocus());

        center.widthProperty().addListener((obs, oldVal, newVal) -> centerImage());
        center.heightProperty().addListener((obs, oldVal, newVal) -> centerImage());

        root.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.Q) {
                CloseImageFile.execute();
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

                root.setCursor(Cursor.CLOSED_HAND);
            }
        });

        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }

    private void setContent(ImageFile file) {
        if (file == null) {
            content.setImage(LoadImage.execute("erro_imagem.png"));
            tittle.setText("No image loaded");
            return;
        }

        String imagePath = file.getContent();

        Image image = LoadImage.execute(
            imagePath == null || imagePath.isBlank()
                ? "erro_imagem.png"
                : imagePath
        );

        content.setImage(image);

        centerImage();

        String imageName = file.getMetadata().name();
        tittle.setText(
            imageName == null || imageName.isBlank()
                ? "No image loaded"
                : imageName
        );

        center.setFocusTraversable(true);
    }

    private void reset() {
        scale = 1.0;
        content.setScaleX(1.0);
        content.setScaleY(1.0);
        content.setTranslateX(0);
        content.setTranslateY(0);
    }

    private void zoom(double factor) {
        scale *= factor;

        scale = Math.max(0.1, Math.min(scale, 10.0));

        content.setScaleX(scale);
        content.setScaleY(scale);

        clampPosition();
    }

    private void move(double x, double y) {
        content.setTranslateY(content.getTranslateY() + y);
        content.setTranslateX(content.getTranslateX() + x);

        clampPosition();
    }

    private void clampPosition() {
        double imageWidth = content.getBoundsInParent().getWidth();
        double imageHeight = content.getBoundsInParent().getHeight();

        double viewWidth = center.getWidth();
        double viewHeight = center.getHeight();

        double maxOffsetX = Math.max(0, (imageWidth - viewWidth) / 2);
        double maxOffsetY = Math.max(0, (imageHeight - viewHeight) / 2);

        if (maxOffsetX == 0) {
            content.setTranslateX(0);
        } else {
            content.setTranslateX(
                Math.max(-maxOffsetX,
                        Math.min(maxOffsetX, content.getTranslateX()))
            );
        }

        if (maxOffsetY == 0) {
            content.setTranslateY(0);
        } else {
            content.setTranslateY(
                Math.max(-maxOffsetY,
                        Math.min(maxOffsetY, content.getTranslateY()))
            );
        }
    }

    private void centerImage() {
        double w = content.getBoundsInLocal().getWidth();
        double h = content.getBoundsInLocal().getHeight();

        content.setLayoutX((center.getWidth() - w) / 2);
        content.setLayoutY((center.getHeight() - h) / 2);
    }
}