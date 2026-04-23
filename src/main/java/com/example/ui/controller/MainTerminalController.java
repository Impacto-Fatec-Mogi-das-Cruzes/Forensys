package com.example.ui.controller;

//import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
//import javafx.util.Duration;

import java.net.URL;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
import java.util.*;

import com.example.ui.parser.CommandParser;
import com.example.ui.parser.FileStructureParser;
import com.example.ui.parser.ParsedCommand;
import com.example.core.command.TerminalCommand;
import com.example.core.command.concrete.DuckCommand;
import com.example.core.filestructure.FileSystemEntry;
import com.example.service.CommandRegistry;

public class MainTerminalController implements Initializable {
    @FXML
    private Label statusIndicator;

    @FXML
    private Label statusLabel;

    @FXML
    private Label caseIdLabel;

    @FXML
    private Label userLabel;

    @FXML
    private Label clockLabel;

    @FXML
    private Label terminalStatusLabel;

    @FXML
    private Label commandCountLabel;

    @FXML
    private Label scanlineLabel;

    @FXML
    private Label promptLabel;

    @FXML
    private Label cursorLabel;

    @FXML
    private Label memoryLabel;

    @FXML
    private Label diskLabel;

    @FXML
    private Label modeLabel;

    @FXML
    private Label systemMessageLabel;

    @FXML
    private Label errorCountLabel;



    @FXML private ScrollPane terminalScrollPane;
    @FXML private VBox terminalOutput;
    @FXML private TextField commandInput;
    // TODO: add a context class to get current position and apply memento pattern to save navigation history
    private FileSystemEntry root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerAllCommands();
        this.root = FileStructureParser.getInstance().parse("example.json");
        this.root.getType();
        /*
        TODO: implement this on the "go" command =)

        FileSystemEntry root = FileStructureParser.getInstance().parse("example.json");
        if (root instanceof Directory dir) {
            System.out.println(dir.getChildren().get("file"));
        } else if (root instanceof TextFile text) {
            System.out.println(dir.getContent());
        }
        */
    }
    
    // TODO: change to new integration with the interfaces
    @FXML
    private void onCommandEntered() {
        String rawInput = commandInput.getText().trim();
        if (rawInput.isEmpty()) return;

        CommandParser commandParser = CommandParser.getInstance();
        ParsedCommand parsedCommand = commandParser.parse(rawInput);

        TerminalCommand command = CommandRegistry.getInstance().get(parsedCommand.command());
        
        // TODO: implement a class to format output
        String output = "", styleClass = "";
        if (command != null) {
            // command.run(parsedCommand.args());
            // output = command.getOutput();
            styleClass = "term-line-system";
        } else {
            output = "Invalid Command, please enter a valid command or use the {help} command for more information";
            styleClass = "term-line-error";
        }
        addTerminalLine(output, styleClass);   

        scrollToBottom();
        commandInput.clear();
    }

    public void addTerminalLine(String text, String styleClass) {
        Label line = new Label(text);
        line.getStyleClass().add(styleClass);
        line.setMaxWidth(Double.MAX_VALUE);

        terminalOutput.getChildren().add(line);
        scrollToBottom();
    }
    
    public void addBlankLine() {
        addTerminalLine("", "term-line-system");
    }

    private void scrollToBottom() {
        Platform.runLater(() -> terminalScrollPane.setVvalue(1.0));
    }

    private void registerAllCommands() {
        CommandRegistry commandRegistry =  CommandRegistry.getInstance();
        DuckCommand duckCommand = new DuckCommand();
        commandRegistry.register(duckCommand.getCommandName(), duckCommand);
    }

    // Some Template Methods
/* 
    private void printBootSequence() {
        List<String[]> lines = List.of(
            new String[]{"════════════════════════════════════════════════════════════════════", "term-line-divider"},
            new String[]{"    FORENSYS  —  SISTEMA DE ANÁLISE FORENSE  v2.1.4", "term-line-header"},
            new String[]{"    Copyright (C) 1992-1994 DigForce Soluções Ltda.", "term-line-system"},
            new String[]{"════════════════════════════════════════════════════════════════════", "term-line-divider"},
            new String[]{"", "term-line-system"},
            new String[]{"  Iniciando subsistemas de análise..............  [OK]", "term-line-system"},
            new String[]{"  Carregando banco de dados forense..............  [OK]", "term-line-system"},
            new String[]{"  Verificando integridade dos arquivos............  [OK]", "term-line-system"},
            new String[]{"  Conectando ao servidor central.................  [OK]", "term-line-system"},
            new String[]{"", "term-line-system"},
            new String[]{"  ► CASO CARREGADO: INV-1994-0047", "term-line-warning"},
            new String[]{"    TÍTULO: Homicídio no Armazém Oliveira", "term-line-system"},
            new String[]{"    DATA  : 19 de Outubro de 1994", "term-line-system"},
            new String[]{"    STATUS: EM INVESTIGAÇÃO — 4 dias em aberto", "term-line-system"},
            new String[]{"", "term-line-system"},
            new String[]{"════════════════════════════════════════════════════════════════════", "term-line-divider"},
            new String[]{"  Digite AJUDA para ver os comandos disponíveis.", "term-line-system"},
            new String[]{"════════════════════════════════════════════════════════════════════", "term-line-divider"},
            new String[]{"", "term-line-system"}
        );

        for (int i = 0; i < lines.size(); i++) {
            final int idx = i;
            String[] entry = lines.get(idx);
            Timeline delay = new Timeline(
                new KeyFrame(Duration.millis(idx * 90), e ->
                    addTerminalLine(entry[0], entry[1])
                )
            );
            delay.play();
        }
    }

    private void processCommand(String rawInput) {
        String[] parts = rawInput.split("\\s+", 2);
        String cmd = parts[0];
        String args = parts.length > 1 ? parts[1].toLowerCase() : "";

        double processingTime = 200 + Math.random() * 400;
        terminalStatusLabel.setText("PROCESSANDO");
        terminalStatusLabel.getStyleClass().setAll("terminal-status-busy");

        Timeline process = new Timeline(new KeyFrame(
            Duration.millis(processingTime),
            e -> {
                executeCommand(cmd, args);
                terminalStatusLabel.setText("PRONTO");
                terminalStatusLabel.getStyleClass().setAll("terminal-status-ready");
            }
        ));
        process.play();
    }

    private void executeCommand(String cmd, String args) {
        switch (cmd) {
            case "AJUDA", "HELP", "?" -> cmdHelp();
            case "LISTAR", "LS", "DIR" -> cmdList(args);
            case "ABRIR", "OPEN" -> cmdOpen(args);
            case "SCAN", "ESCANEAR" -> cmdScan(args);
            case "ANALISAR", "ANALYZE" -> cmdAnalyze(args);
            case "BUSCAR", "FIND", "SEARCH" -> cmdSearch(args);
            case "LIMPAR", "CLEAR", "CLS" -> cmdClear();
            case "STATUS" -> cmdStatus();
            case "CASO", "CASE" -> cmdCase();
            case "SUJEITOS", "SUBJECTS" -> cmdSubjects();
            case "CD" -> cmdCd(args);
            case "SAIR", "EXIT", "QUIT" -> cmdExit();
            default -> cmdUnknown(cmd);
        }
    }

    private void cmdHelp() {
        addBlankLine();
        addTerminalLine("╔══ COMANDOS DISPONÍVEIS ══════════════════════════════════╗", "term-line-divider");
        addTerminalLine("║  LISTAR [pasta]   — Lista arquivos do diretório          ║", "term-line-system");
        addTerminalLine("║  ABRIR <arquivo>  — Abre e exibe o conteúdo de um arquivo║", "term-line-system");
        addTerminalLine("║  SCAN <pasta>     — Escaneia pasta em busca de evidências║", "term-line-system");
        addTerminalLine("║  ANALISAR <arq>   — Análise profunda de arquivo/evidência║", "term-line-system");
        addTerminalLine("║  BUSCAR <termo>   — Busca termo em todos os arquivos     ║", "term-line-system");
        addTerminalLine("║  SUJEITOS         — Lista todos os sujeitos do caso      ║", "term-line-system");
        addTerminalLine("║  CASO             — Exibe informações do caso ativo      ║", "term-line-system");
        addTerminalLine("║  STATUS           — Status atual do sistema              ║", "term-line-system");
        addTerminalLine("║  CD <pasta>       — Navega entre diretórios              ║", "term-line-system");
        addTerminalLine("║  LIMPAR           — Limpa o terminal                     ║", "term-line-system");
        addTerminalLine("╚══════════════════════════════════════════════════════════╝", "term-line-divider");
        addBlankLine();
        addTerminalLine("  DICA: Use ↑ e ↓ para navegar pelo histórico de comandos.", "term-line-system");
        addBlankLine();
    }

    private void cmdClear() {
        terminalOutput.getChildren().clear();
        addTerminalLine("  Terminal limpo.", "term-line-system");
        addBlankLine();
    }

    private void cmdExit() {
        addBlankLine();
        addTerminalLine("  Encerrando sessão de DET.SOUZA...", "term-line-warning");
        addTerminalLine("  Salvando log de atividades...", "term-line-system");
        addTerminalLine("  Até a próxima, Detetive.", "term-line-output");
        addBlankLine();
        commandInput.setDisable(true);
        terminalStatusLabel.setText("OFFLINE");
        terminalStatusLabel.getStyleClass().setAll("terminal-status-error");
        statusIndicator.getStyleClass().setAll("status-dot-offline");
        statusLabel.setText("DESCONECTADO");
    }

    private void cmdUnknown(String cmd) {
        addTerminalLine("  ERRO: Comando desconhecido — '" + cmd + "'", "term-line-error");
        addTerminalLine("  Digite AJUDA para ver os comandos disponíveis.", "term-line-system");
        errorCount++;
        updateErrorCount();
        addBlankLine();
    } 
*/
}
