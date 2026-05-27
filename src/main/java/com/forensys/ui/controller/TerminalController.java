package com.forensys.ui.controller;

import java.util.Optional;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommand;
import com.forensys.service.terminal.ContinueExecution;
import com.forensys.service.terminal.GetPendingExecution;
import com.forensys.service.terminal.GetPendingOperation;
import com.forensys.service.terminal.HandleCommand;
import com.forensys.service.terminal.HandleOutput;
import com.forensys.service.terminal.HandlePendingOperation;
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

        CommandOutput output = null;

        if (GetPendingOperation.execute() != null) {
            output = HandlePendingOperation.execute(rawInput);
            if (GetPendingExecution.execute() != null) {
                output = Optional.ofNullable(ContinueExecution.execute()).orElse(output);
            }
        } else {
            ParsedCommand parsedCommand = ParseCommand.execute(rawInput);
            output = HandleCommand.execute(parsedCommand);
        }

        HandleOutput.init(outputArea);
        HandleOutput.execute(output);

        inputField.clear();
    }

    @FXML
    private void exitGame() {
        Platform.exit();
    }
}
