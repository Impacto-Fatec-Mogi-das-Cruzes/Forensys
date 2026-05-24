package com.forensys.ui.components;

import java.io.InputStream;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ImageComponent extends VBox {
    private final Label imageOwner = new Label();
    private final ImageView imageView = new ImageView();
    private final Label imageTime = new Label();

    public ImageComponent() {
        this.getChildren().addAll(
            imageOwner,
            imageView,
            imageTime
        );

        this.getStyleClass().add("image-component");
        imageOwner.getStyleClass().add("image-owner");
        imageView.getStyleClass().add("image-view");
        imageTime.getStyleClass().add("image-time");
    }

    public void setContent(
            String owner,
            String color,
            String time,
            String imagePath,
            double imageHeight,
            double imageWidth
    ) {

        imageOwner.setText(owner);

        imageOwner.setStyle("-fx-text-fill: " + color + ";");

        imageTime.setText(time);

        InputStream stream = getClass().getResourceAsStream(imagePath);

        if (stream != null) {

            imageView.setImage(new Image(stream));

            imageView.setFitWidth(imageWidth);

            imageView.setFitHeight(imageHeight);

            imageView.setPreserveRatio(true);
        }
    }
}