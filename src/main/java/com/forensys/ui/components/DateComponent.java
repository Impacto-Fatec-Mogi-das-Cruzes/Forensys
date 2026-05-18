package com.forensys.ui.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DateComponent extends VBox {
    private final Label dateLabel = new Label();

    public DateComponent() {
        this.getChildren().add(dateLabel);
    }

    public void setContent(String date) {
        
        dateLabel.setText(date);

        dateLabel.setStyle(
            "-fx-text-fill: #999999;"
        );

        this.setStyle(
                "-fx-alignment: center;"
        );
    }
}
