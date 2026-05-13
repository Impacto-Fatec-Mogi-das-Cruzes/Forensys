package com.forensys.controller;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandParser;
import com.forensys.core.command.OutputSegment;
import com.forensys.core.command.ParsedCommand;
import com.forensys.core.command.concrete.chat.ChatCommand;
import com.forensys.core.command.concrete.clear.ClearCommand;
import com.forensys.core.command.concrete.duck.DuckCommand;
import com.forensys.core.command.concrete.go.GoCommand;
import com.forensys.core.command.concrete.help.HelpCommand;
import com.forensys.core.command.concrete.list.ListCommand;
import com.forensys.core.command.concrete.read.ReadCommand;
import com.forensys.core.command.concrete.say.SayCommand;
import com.forensys.service.CommandHandler;
import com.forensys.service.RegisterAllCommands;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
        new RegisterAllCommands(
            new DuckCommand(),
            new SayCommand(),
            new GoCommand(),
            new ListCommand(),
            new ReadCommand(),
            new ChatCommand(),
            new HelpCommand(),
            new ClearCommand()
        ).execute();

        scrollPane.vvalueProperty().bind(outputArea.heightProperty());
    }

    @FXML
    private void commandEntered() {
        //TODO: Divide this process in different methods
        String rawInput = inputField.getText().trim();
        if (rawInput.isEmpty()) return;
        ParsedCommand parsedCommand = CommandParser.getInstance().parse(rawInput);

        
        CommandOutput output = CommandHandler.getInstance().handle(parsedCommand);        

        if (output.doesClearScreen()) {
            outputArea.getChildren().clear();
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
            outputArea.getChildren().add(textSegment);
        }
        outputArea.getChildren().add(new Text("\n"));
        inputField.clear(); 
       
    }

    @FXML
    private void exitGame() {
        Platform.exit();
    }
}
