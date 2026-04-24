package com.forensys.ui.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.forensys.core.ApplicationContext;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.DuckCommand;
import com.forensys.core.command.concrete.GoCommand;
import com.forensys.core.command.concrete.ListCommand;
import com.forensys.core.command.concrete.SayCommand;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.Directory;
import com.forensys.service.CommandHandler;
import com.forensys.service.CommandRegistry;
import com.forensys.ui.command.CommandParser;
import com.forensys.ui.command.ParsedCommand;
import com.forensys.ui.filestructure.FileStructureParser;

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

        ApplicationContext context = ApplicationContext.getInstance();
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
        //TODO: Divide this process in different methods
        String rawInput = inputField.getText().trim();
        if (rawInput.isEmpty()) return;
        CommandParser commandParser = CommandParser.getInstance();
        ParsedCommand parsedCommand = commandParser.parse(rawInput);

        CommandOutput output = CommandHandler.getInstance().handle(parsedCommand);        
        String styleClass = "";

        if (output.getExitCode() == CommandExitCode.SUCESS) {
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
