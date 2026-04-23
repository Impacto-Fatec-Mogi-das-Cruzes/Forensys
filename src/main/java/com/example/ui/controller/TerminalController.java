package com.example.ui.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.core.SystemContext;
import com.example.core.command.CommandExitCode;
import com.example.core.command.CommandOutput;
import com.example.core.command.TerminalCommand;
import com.example.core.command.concrete.DuckCommand;
import com.example.core.command.concrete.GoCommand;
import com.example.core.command.concrete.ListCommand;
import com.example.core.command.concrete.SayCommand;
import com.example.core.filestructure.FileSystemEntry;
import com.example.core.filestructure.concrete.Directory;
import com.example.service.CommandHandler;
import com.example.service.registry.CommandRegistry;
import com.example.ui.parser.CommandParser;
import com.example.ui.parser.FileStructureParser;
import com.example.ui.parser.ParsedCommand;

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

        SystemContext context = SystemContext.getInstance();
        FileStructureParser fileStructureParser = FileStructureParser.getInstance();
        FileSystemEntry root = fileStructureParser.parse("example.json");
        context.setCurrentDirectory((Directory) root);

        List<TerminalCommand> commandsToRegister = new ArrayList<>();
        commandsToRegister.addAll(Arrays.asList(
            new DuckCommand(),
            new SayCommand(),
            new GoCommand(),
            new ListCommand()
        ));
        registerAllCommands(commandsToRegister);
    }

    private void registerAllCommands(List<TerminalCommand> commandsToRegister) {
        CommandRegistry commandRegistry =  CommandRegistry.getInstance();
        for (TerminalCommand terminalCommand : commandsToRegister) {
            commandRegistry.register(terminalCommand.getCommandName(), terminalCommand);
        }
    }

    @FXML
    private void commandEntered() {

        String rawInput = inputField.getText().trim();
        if (rawInput.isEmpty()) return;
        CommandParser commandParser = CommandParser.getInstance();
        ParsedCommand parsedCommand = commandParser.parse(rawInput);

        CommandOutput output = CommandHandler.getInstance().handle(parsedCommand);        
        String styleClass = "";

        if (output.getExitCode() == CommandExitCode.SUCESS) {
            styleClass = "term-line-system";
        } else if (output.getExitCode() == CommandExitCode.FAILURE) {
            styleClass = "term-line-error";
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
