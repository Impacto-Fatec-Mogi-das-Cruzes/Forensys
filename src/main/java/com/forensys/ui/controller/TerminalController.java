package com.forensys.ui.controller;

import java.util.List;
import java.util.Optional;

import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommand;
import com.forensys.service.terminal.ContinueExecution;
import com.forensys.service.terminal.DefineIntialOutput;
import com.forensys.service.terminal.GetPendingExecution;
import com.forensys.service.terminal.GetPendingOperation;
import com.forensys.service.terminal.HandleCommand;
import com.forensys.service.terminal.HandleOutput;
import com.forensys.service.terminal.HandlePendingOperation;
import com.forensys.service.terminal.OpenInitialTextFile;
import com.forensys.service.terminal.ParseCommand;
import com.forensys.service.terminal.RegisterAllCommands;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

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

        Platform.runLater(() -> {
            printBootSequence();
        });
    }

    private void printBootSequence() {
        List<BootSequenceEntry> sequence = List.of(
            new BootSequenceEntry("", 0),
            new BootSequenceEntry("════════════════════════════════════════════════════════════════════", 100),
            new BootSequenceEntry("    ALFA TEC  —  FORENSIC ANALYSIS SYSTEM  v2.1.4", 100),
            new BootSequenceEntry("    Copyright (C) 1992-1994 Alfa Tec Ltd.", 100),
            new BootSequenceEntry("════════════════════════════════════════════════════════════════════", 100),
            new BootSequenceEntry("", 100),
            new BootSequenceEntry("  ► INITIALIZING SUBSYSTEMS...", 200),
            new BootSequenceEntry("    Loading BIOS.............................. [OK]", 150),
            new BootSequenceEntry("    Initializing hardware drivers...... [OK]", 150),
            new BootSequenceEntry("    Mounting file systems................ [OK]", 150),
            new BootSequenceEntry("", 100),
            new BootSequenceEntry("  ► EXECUTING FILE RECOVERY...", 200),
            new BootSequenceEntry("    Scanning disk clusters................ [STARTED]", 150),
            new BootSequenceEntry("    Analyzing FAT32.............................. [PROCESSING]", 80),
            new BootSequenceEntry("    ████████████████░░░░░░░░░░░░░░░░░░░░░░░░ 45%", 80),
            new BootSequenceEntry("    ██████████████████████░░░░░░░░░░░░░░░░░░░ 55%", 80),
            new BootSequenceEntry("    ████████████████████████████░░░░░░░░░░░░░ 70%", 80),
            new BootSequenceEntry("    ███████████████████████████████████░░░░░░ 90%", 80),
            new BootSequenceEntry("    ████████████████████████████████████████░ 99%", 80),
            new BootSequenceEntry("    █████████████████████████████████████████ [COMPLETED]", 150),
            new BootSequenceEntry("", 100),
            new BootSequenceEntry("    Files recovered:", 100),
            new BootSequenceEntry("      • ████.247 fragmented files", 80),
            new BootSequenceEntry("      • ░2 recoverable deleted files", 80),
            new BootSequenceEntry("      • 8█░█ MB of data recovered", 80),
            new BootSequenceEntry("", 100),
            new BootSequenceEntry("  ► VERIFYING DATA INTEGRITY...", 200),
            new BootSequenceEntry("    Verifying checksums........................ [OK]", 150),
            new BootSequenceEntry("    Validating file signatures............. [OK]", 150),
            new BootSequenceEntry("    Connecting to forensic database......... [OK]", 150),
            new BootSequenceEntry("", 100),
            new BootSequenceEntry("  ► OPENING ANALYSIS REPORT...", 200),
            new BootSequenceEntry("    Decompressing report file.......... [OK]", 150),
            new BootSequenceEntry("    Loading evidence into memory............. [OK]", 150),
            new BootSequenceEntry("    Opening report.txt...", 100),
            new BootSequenceEntry("", 2000)
        );

        SequentialTransition sequentialTransition = new SequentialTransition();

        for (BootSequenceEntry entry : sequence) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(entry.delayAfter), e -> {
                if (!entry.text.isEmpty()) {
                    Text line = new Text(entry.text);
                    line.getStyleClass().add("default");
                    outputArea.getChildren().add(line);
                    outputArea.getChildren().add(new Text("\n"));
                } else {
                    outputArea.getChildren().add(new Text("\n"));
                }
            }));
            sequentialTransition.getChildren().add(timeline);
        }

        sequentialTransition.setOnFinished(e -> {
            OpenInitialTextFile.execute();
            outputArea.getChildren().clear();
            DefineIntialOutput.execute(outputArea);
        });
        sequentialTransition.play();
    }

    private static class BootSequenceEntry {
        String text;
        int delayAfter;

        BootSequenceEntry(String text, int delayAfter) {
            this.text = text;
            this.delayAfter = delayAfter;
        }
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
