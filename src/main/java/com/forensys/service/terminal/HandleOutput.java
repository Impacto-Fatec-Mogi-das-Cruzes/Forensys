package com.forensys.service.terminal;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.OutputSegment;
import com.forensys.core.command.SegmentDecoration;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class HandleOutput {

    private static TextFlow container;

    public static void init(TextFlow outputArea) {
        container = outputArea;
    }

    public static TextFlow execute(CommandOutput output) {
        if (output.doesClearScreen()) {
            container.getChildren().clear();
        }

        Text textSegment = null;

        for (OutputSegment segment : output.getSegments()) {
            textSegment = new Text(segment.getText());
            textSegment.setStyle("-fx-fill: " + segment.getColor() + ";");
            for (SegmentDecoration style : segment.getStyles()) {
                textSegment.getStyleClass().add(style.styleClass());
            }
            container.getChildren().add(textSegment);
        }
        container.getChildren().add(new Text("\n"));
        return container;
    }
}
