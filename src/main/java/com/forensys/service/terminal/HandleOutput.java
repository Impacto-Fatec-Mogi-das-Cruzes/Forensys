package com.forensys.service.terminal;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.OutputSegment;

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
        String styleClass = "";

        if (output.getExitCode() == CommandExitCode.SUCCESS) {
            styleClass = "system";
        } else if (output.getExitCode() == CommandExitCode.FAILURE) {
            styleClass = "error";
        }

        Text textSegment = null;

        for (OutputSegment segment : output.getSegments()) {
            textSegment = new Text(segment.getText());
            textSegment.getStyleClass().add(styleClass);
            container.getChildren().add(textSegment);
        }
        container.getChildren().add(new Text("\n"));
        return container;
    }
}
