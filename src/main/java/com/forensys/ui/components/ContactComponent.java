package com.forensys.ui.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ContactComponent extends HBox {
    private final Label titleLabel = new Label();

    public ContactComponent() {
        this.getChildren().add(titleLabel);
    }

    public void setContent(String tittle) {
        titleLabel.setText(tittle);
        titleLabel.setStyle("-fx-text-fill: #cbd5e1;");
    }
}
