package com.forensys.service.terminal;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DefineIntialOutput {
    public static void execute(TextFlow outputArea) {
        String styleClass = "default";
        Text text = new Text("AlfaDyne Recovery System v1.4\r\n" +
                        "\n" +
                        "Recovered device:\r\n" +
                        "ARES-NOTEBOOK\n" +
                        "\n" +
                        "Type 'help' to view available commands.\n");
        text.getStyleClass().add(styleClass);
        outputArea.getChildren().add(text);
    }
}
