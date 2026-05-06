package com.forensys.ui.controller;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandRegistry;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.duck.DuckCommand;
import com.forensys.core.command.concrete.go.GoCommand;
import com.forensys.core.command.concrete.list.ListCommand;
import com.forensys.core.command.concrete.read.ReadCommand;
import com.forensys.core.command.concrete.say.SayCommand;
import com.forensys.service.CommandHandler;
import com.forensys.ui.command.CommandParser;
import com.forensys.ui.command.ParsedCommand;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TerminalController {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox outputArea;

    @FXML
    private TextField inputField;

    @FXML
    private void initialize() {
        registerAllCommands(
            new DuckCommand(),
            new SayCommand(),
            new GoCommand(),
            new ListCommand(),
            new ReadCommand()
        );
    }

    //TODO: make it a service
    private void registerAllCommands(TerminalCommand... commandsToRegister) {
        CommandRegistry commandRegistry =  CommandRegistry.getInstance();
        for (TerminalCommand terminalCommand : commandsToRegister) {
            commandRegistry.register(terminalCommand.getCommandName(), terminalCommand);
        }
    }

    @FXML
    private void commandEntered() {
        //TODO: Divide this process in different methods
        String rawInput = inputField.getText().trim();
        if (rawInput.isEmpty()) return;
        CommandParser commandParser = CommandParser.getInstance();
        ParsedCommand parsedCommand = commandParser.parse(rawInput);

        CommandOutput output = CommandHandler.getInstance().handle(parsedCommand);        
        String styleClass = "";

        if (output.getExitCode() == CommandExitCode.SUCCESS) {
            styleClass = "system";
        } else if (output.getExitCode() == CommandExitCode.FAILURE) {
            styleClass = "error";
        }

        Label lineLabel = null;
        
        for (String line : output.getSegments()) {
            lineLabel = new Label(line);
            lineLabel.getStyleClass().add(styleClass);
            lineLabel.setMaxWidth(Double.MAX_VALUE);
            outputArea.getChildren().add(lineLabel);
        }
        inputField.clear();
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }

    @FXML
    private void exitGame() {
        Platform.exit();
    }
}
