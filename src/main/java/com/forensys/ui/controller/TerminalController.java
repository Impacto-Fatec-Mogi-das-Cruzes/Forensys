package com.forensys.ui.controller;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommand;
import com.forensys.service.terminal.HandleCommand;
import com.forensys.service.terminal.HandleOutput;
import com.forensys.service.terminal.ParseCommand;
import com.forensys.service.terminal.RegisterAllCommands;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

public class TerminalController {
    @FXML
    private TextFlow outputArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField inputField;

    @FXML
    private void initialize() {
        RegisterAllCommands.execute();

        scrollPane.vvalueProperty().bind(outputArea.heightProperty());
    }

    @FXML
    private void commandEntered() {
        String rawInput = inputField.getText().trim();
        if (rawInput.isEmpty()) return;

        ParsedCommand parsedCommand = ParseCommand.execute(rawInput);
        CommandOutput output = HandleCommand.execute(parsedCommand);

        HandleOutput.init(outputArea);
        HandleOutput.execute(output);

        inputField.clear();
    }

    @FXML
    private void exitGame() {
        Platform.exit();
    }
}
