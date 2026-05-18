package com.forensys.ui.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MessageComponent extends VBox {

    private final HBox header = new HBox();
    private final Label ownerLabel = new Label();
    private final Label timeLabel = new Label();
    private final Label textLabel = new Label();

    public MessageComponent() {

        header.getChildren().addAll(
                ownerLabel,
                timeLabel);

        textLabel.setWrapText(true);
        textLabel.maxWidthProperty().bind(
            this.widthProperty().subtract(60)
        );
        this.getChildren().addAll(
                header,
                textLabel);
    }

    public void setContent(
        String owner,
        String color,
        String text,
        String time
    ) {
        header.setStyle("-fx-alignment: BOTTOM_LEFT;-fx-spacing: 5;");

        ownerLabel.setText(owner);

        ownerLabel.setStyle(
                "-fx-text-fill: " + color + ";" +
                "-fx-font-weight: bold;");

        textLabel.setText(text);
        textLabel.setStyle("-fx-text-fill: #cbd5e1;");

        timeLabel.setText(time);

        timeLabel.setStyle(
                "-fx-text-fill: #999999;" +
                "-fx-font-size: " +
                (ownerLabel.getFont().getSize() * 0.8) +
                "px;");
    }
}