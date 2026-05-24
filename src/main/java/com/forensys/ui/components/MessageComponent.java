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
        this.getStyleClass().add("message-component");
        header.getStyleClass().add("message-header");
        ownerLabel.getStyleClass().add("message-owner");
        timeLabel.getStyleClass().add("message-time");
        textLabel.getStyleClass().add("message-text");

        header.getChildren().addAll(
                ownerLabel,
                timeLabel);

        textLabel.setWrapText(true);

        textLabel.maxWidthProperty().bind(this.widthProperty().subtract(60));

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
        ownerLabel.setText(owner);
        ownerLabel.setStyle("-fx-text-fill: " + color + ";");
        textLabel.setText(text);
        timeLabel.setText(time);
    }
}