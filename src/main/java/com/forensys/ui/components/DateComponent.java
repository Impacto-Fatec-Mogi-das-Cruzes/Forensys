package com.forensys.ui.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DateComponent extends VBox {
    private final Label dateLabel = new Label();

    public DateComponent() {
        this.getChildren().add(dateLabel);
        
        this.getStyleClass().add("date-component");
        dateLabel.getStyleClass().add("date-label");
    }

    public void setContent(String date) {
        dateLabel.setText(date);
    }
}
