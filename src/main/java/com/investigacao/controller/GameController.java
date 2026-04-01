package com.investigacao.controller;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GameController implements Initializable {

    @FXML private Label clockLabel;
    @FXML private Label statusIndicator;
    @FXML private Label statusLabel;
    @FXML private Label caseIdLabel;
    @FXML private Label userLabel;
    @FXML private Label currentPathLabel;
    @FXML private Label selectedFileName;
    @FXML private Label selectedFileSize;
    @FXML private Label selectedFileDate;
    @FXML private Label terminalStatusLabel;
    @FXML private Label commandCountLabel;
    @FXML private Label systemMessageLabel;
    @FXML private Label memoryLabel;
    @FXML private Label diskLabel;
    @FXML private Label modeLabel;
    @FXML private Label errorCountLabel;
    @FXML private Label promptLabel;
    @FXML private Label cursorLabel;

    @FXML private ScrollPane terminalScrollPane;
    @FXML private VBox terminalOutput;
    @FXML private TextField commandInput;

    private int commandCount = 0;
    private int errorCount = 0;
    private final List<String> commandHistory = new ArrayList<>();
    private int historyIndex = -1;

    private String currentDirectory = "C:\\FORENSYS\\CASOS\\1994";

    private final Map<String, List<String>> fileSystem = new LinkedHashMap<>() {{
        put("LOGS",      List.of("sistema.log", "acesso.log", "erros.log", "rede_23out.log"));
        put("EVIDENCIAS",List.of("foto_cena_01.img", "foto_cena_02.img", "audio_01.wav", "impressoes.dat"));
        put("SUJEITOS",  List.of("sujeito_A.dat", "sujeito_B.dat", "desconhecido.dat"));
        put("ARQUIVOS",  List.of("relatorio_pericial.txt", "laudo_medico.enc", "mapa_local.bin"));
        put("SISTEMA",   List.of("config.sys", "autoexec.bat", "forensys.exe"));
    }};

    private final Map<String, String[]> fileContents = new HashMap<>() {{
        put("sistema.log", new String[]{
            "=== LOG DO SISTEMA — FORENSYS v2.1.4 ===",
            "23/10/1994 21:03:14 — Sistema iniciado",
            "23/10/1994 21:03:15 — Banco de dados conectado",
            "23/10/1994 23:41:07 — [AVISO] Tentativa de acesso não autorizado",
            "23/10/1994 23:41:09 — Acesso bloqueado. IP: 192.168.1.47",
            "23/10/1994 23:47:31 — Login: DET.SOUZA — AUTORIZADO"
        });
        put("sujeito_A.dat", new String[]{
            "=== FICHA DO SUJEITO — REGISTRO A ===",
            "NOME    : [REDACTED]",
            "IDADE   : 34 anos",
            "OCUPAÇÃO: Contador",
            "STATUS  : Pessoa de interesse",
            "OBS     : Visto pela última vez em 21/10/1994",
            "          Alibi não confirmado para 19/10/1994",
            "[ARQUIVO PARCIALMENTE CORROMPIDO]"
        });
        put("relatorio_pericial.txt", new String[]{
            "=== RELATÓRIO PERICIAL — CASO INV-1994-0047 ===",
            "Data de emissão: 23/10/1994",
            "Perito Responsável: Dr. Almeida",
            "",
            "SUMÁRIO:",
            "  Corpo encontrado às 06:14h do dia 19/10/1994.",
            "  Local: Armazém Oliveira, Rua das Palmeiras, 87.",
            "  Causa mortis: A ser determinada.",
            "",
            "OBSERVAÇÕES TÉCNICAS:",
            "  > Presença de substância não identificada (amostra #3)",
            "  > Marcas de impacto contundente no occipital",
            "  > [SEÇÃO 4 — ACESSO RESTRITO — NÍVEL 3 NECESSÁRIO]"
        });
    }};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupClock();
        setupCursorBlink();
        setupGlitchEffect();
        printBootSequence();

        Platform.runLater(() -> commandInput.requestFocus());
    }

    private String getFileIcon(String fileName) {
        if (fileName.endsWith(".log")) return "[L]";
        if (fileName.endsWith(".dat")) return "[D]";
        if (fileName.endsWith(".txt")) return "[T]";
        if (fileName.endsWith(".img")) return "[I]";
        if (fileName.endsWith(".wav")) return "[A]";
        if (fileName.endsWith(".enc")) return "[E]";
        if (fileName.endsWith(".bin")) return "[B]";
        return "[-]";
    }

    private void setupClock() {
        Timeline clock = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                clockLabel.setText(time);
            })
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void setupCursorBlink() {
        Timeline blink = new Timeline(
            new KeyFrame(Duration.millis(530), e -> cursorLabel.setVisible(true)),
            new KeyFrame(Duration.millis(1060), e -> cursorLabel.setVisible(false))
        );
        blink.setCycleCount(Animation.INDEFINITE);
        blink.play();
    }

    private void setupGlitchEffect() {
        scheduleNextGlitch();
    }

    private void scheduleNextGlitch() {
        double delay = 8000 + Math.random() * 12000;
        Timeline glitchTimer = new Timeline(
            new KeyFrame(Duration.millis(delay), e -> triggerGlitch())
        );
        glitchTimer.play();
    }

    private void triggerGlitch() {
        int type = (int)(Math.random() * 3);

        switch (type) {
            case 0 -> glitchStatusBar();
            case 1 -> glitchTerminalLine();
            case 2 -> glitchPrompt();
        }

        scheduleNextGlitch();
    }

    private void glitchStatusBar() {
        String original = statusLabel.getText();
        String[] glitchedTexts = {"C0NECT4D0", "C▓N▒CTADO", "ERR_STATE", "CONECTAD█"};
        String glitch = glitchedTexts[(int)(Math.random() * glitchedTexts.length)];

        statusLabel.setText(glitch);
        statusLabel.setStyle("-fx-text-fill: #FF3A3A;");

        new Timeline(new KeyFrame(Duration.millis(150 + Math.random() * 200), e -> {
            statusLabel.setText(original);
            statusLabel.setStyle("");
        })).play();
    }

    private void glitchTerminalLine() {
        String[] corruptedLines = {
            "▒▓░▒▓░ INTERRUPÇÃO DE SINAL ░▓▒░▓▒",
            "ERR:0xC4 — buffer overflow detected",
            "█████████████████████████████████",
            "╔══FALHA DE LEITURA NO SETOR 47══╗",
            "┌─ AVISO: ARQUIVO MODIFICADO ─┐"
        };
        String line = corruptedLines[(int)(Math.random() * corruptedLines.length)];
        addTerminalLine(line, "term-line-glitch");
    }

    private void glitchPrompt() {
        String original = promptLabel.getText();
        promptLabel.setText("SISTEMA@ERR:~# ");
        promptLabel.setStyle("-fx-text-fill: #FF3A3A;");

        new Timeline(new KeyFrame(Duration.millis(200), e -> {
            promptLabel.setText(original);
            promptLabel.setStyle("");
        })).play();
    }

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

    @FXML
    private void onCommandEntered() {
        String raw = commandInput.getText().trim();
        if (raw.isEmpty()) return;

        addTerminalLine(promptLabel.getText() + raw, "term-line-input");

        commandHistory.add(0, raw);
        historyIndex = -1;
        commandCount++;
        commandCountLabel.setText("CMD: " + commandCount);

        commandInput.clear();

        processCommand(raw.toUpperCase());
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

    private void cmdList(String args) {
        String folder = args.isEmpty() ? "TODOS" : args.toUpperCase();
        addBlankLine();

        if (args.isEmpty()) {
            addTerminalLine("  DIRETÓRIOS DISPONÍVEIS:", "term-line-output");
            addBlankLine();
            fileSystem.forEach((dir, files) -> {
                addTerminalLine("  📁 " + dir + "  [" + files.size() + " arquivo(s)]", "term-line-output");
            });
        } else if (fileSystem.containsKey(folder)) {
            addTerminalLine("  ARQUIVOS EM: " + currentDirectory + "\\" + folder, "term-line-output");
            addBlankLine();
            fileSystem.get(folder).forEach(file ->
                addTerminalLine("      " + getFileIcon(file) + "  " + file, "term-line-output")
            );
        } else {
            addTerminalLine("  ERRO: Diretório '" + args.toUpperCase() + "' não encontrado.", "term-line-error");
            errorCount++;
            updateErrorCount();
        }
        addBlankLine();
    }

    private void cmdOpen(String args) {
        if (args.isEmpty()) {
            addTerminalLine("  SINTAXE: ABRIR <nome-do-arquivo>", "term-line-warning");
            return;
        }
        addBlankLine();

        String found = findFile(args);
        if (found != null && fileContents.containsKey(found)) {
            addTerminalLine("  ══ EXIBINDO: " + found.toUpperCase() + " ══", "term-line-header");
            addBlankLine();
            for (String line : fileContents.get(found)) {
                addTerminalLine("  " + line, line.startsWith("[") ? "term-line-warning" : "term-line-data");
            }
        } else if (found != null) {
            addTerminalLine("  [Arquivo encontrado: " + found + "]", "term-line-output");
            addTerminalLine("  Conteúdo binário — use ANALISAR para decodificar.", "term-line-system");
        } else {
            addTerminalLine("  ERRO: Arquivo '" + args + "' não encontrado.", "term-line-error");
            errorCount++;
            updateErrorCount();
        }
        addBlankLine();
    }

    private void cmdScan(String folder) {
        String target = folder.isEmpty() ? "TODOS OS DIRETÓRIOS" : folder.toUpperCase();
        addBlankLine();
        addTerminalLine("  ► INICIANDO SCAN: " + target, "term-line-warning");
        addTerminalLine("  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  100%", "term-line-system");
        addBlankLine();
        addTerminalLine("  RESULTADO DO SCAN:", "term-line-output");
        addTerminalLine("    ● 14 arquivos verificados", "term-line-data");
        addTerminalLine("    ● 2 arquivos com metadados suspeitos", "term-line-warning");
        addTerminalLine("    ● 1 arquivo com assinatura corrompida", "term-line-error");
        addTerminalLine("    ● 0 ameaças de segurança detectadas", "term-line-data");
        addBlankLine();
        addTerminalLine("  ► Use ANALISAR <arquivo> para investigar itens suspeitos.", "term-line-system");
        addBlankLine();
    }

    private void cmdAnalyze(String args) {
        if (args.isEmpty()) {
            addTerminalLine("  SINTAXE: ANALISAR <nome-do-arquivo>", "term-line-warning");
            return;
        }
        addBlankLine();
        addTerminalLine("  ► ANÁLISE FORENSE: " + args.toUpperCase(), "term-line-header");
        addTerminalLine("  Verificando hash MD5........................  OK", "term-line-system");
        addTerminalLine("  Analisando timestamps........................  ", "term-line-system");

        boolean anomaly = Math.random() > 0.4;
        if (anomaly) {
            addTerminalLine("  ► [ANOMALIA DETECTADA] Arquivo modificado após a data do crime!", "term-line-critical");
            addTerminalLine("    Timestamp original : 19/10/1994 — 06:14:22", "term-line-data");
            addTerminalLine("    Última modificação : 21/10/1994 — 02:33:07", "term-line-data");
            addTerminalLine("    Diferença          : 47 horas e 18 minutos", "term-line-data");
        } else {
            addTerminalLine("  Timestamps coerentes com a data do incidente.", "term-line-output");
            addTerminalLine("  Nenhuma anomalia detectada neste arquivo.", "term-line-output");
        }
        addBlankLine();
    }

    private void cmdSearch(String term) {
        if (term.isEmpty()) {
            addTerminalLine("  SINTAXE: BUSCAR <termo>", "term-line-warning");
            return;
        }
        addBlankLine();
        addTerminalLine("  ► BUSCANDO: \"" + term + "\"", "term-line-output");
        addTerminalLine("  ──────────────────────────────────────────", "term-line-divider");

        boolean found = Math.random() > 0.3;
        if (found) {
            addTerminalLine("  LOGS/acesso.log         — linha 47", "term-line-data");
            addTerminalLine("  EVIDENCIAS/foto_cena_01 — metadados", "term-line-data");
            addTerminalLine("  ──────────────────────────────────────────", "term-line-divider");
            addTerminalLine("  2 ocorrência(s) encontrada(s).", "term-line-output");
        } else {
            addTerminalLine("  Nenhuma ocorrência de \"" + term + "\" encontrada.", "term-line-system");
        }
        addBlankLine();
    }

    private void cmdSubjects() {
        addBlankLine();
        addTerminalLine("╔══ SUJEITOS DO CASO INV-1994-0047 ═══════════════╗", "term-line-divider");
        addTerminalLine("║  A — [REDACTED]    — Contador     — INTERESSE   ║", "term-line-warning");
        addTerminalLine("║  B — [REDACTED]    — Comerciante  — SUSPEITO    ║", "term-line-error");
        addTerminalLine("║  ? — DESCONHECIDO  — ---          — PROCURADO   ║", "term-line-glitch");
        addTerminalLine("╚══════════════════════════════════════════════════╝", "term-line-divider");
        addBlankLine();
        addTerminalLine("  Use ABRIR sujeito_A.dat para ver detalhes.", "term-line-system");
        addBlankLine();
    }

    private void cmdCase() {
        addBlankLine();
        addTerminalLine("╔══ CASO ATIVO ═══════════════════════════════════╗", "term-line-header");
        addTerminalLine("║  ID    : INV-1994-0047                          ║", "term-line-data");
        addTerminalLine("║  TÍTULO: Homicídio no Armazém Oliveira          ║", "term-line-data");
        addTerminalLine("║  DATA  : 19 de Outubro de 1994                  ║", "term-line-data");
        addTerminalLine("║  LOCAL : Armazém Oliveira — Rua das Palmeiras   ║", "term-line-data");
        addTerminalLine("║  STATUS: EM INVESTIGAÇÃO                        ║", "term-line-warning");
        addTerminalLine("║  DIAS  : 4 dias em aberto                       ║", "term-line-data");
        addTerminalLine("╚══════════════════════════════════════════════════╝", "term-line-header");
        addBlankLine();
    }

    private void cmdStatus() {
        addBlankLine();
        addTerminalLine("  SISTEMA: FORENSYS v2.1.4 — Operando normalmente", "term-line-output");
        addTerminalLine("  MEMÓRIA: 2.1MB / 4MB utilizados", "term-line-system");
        addTerminalLine("  DISCO  : C: 127MB livres", "term-line-system");
        addTerminalLine("  REDE   : Conectado ao servidor central", "term-line-output");
        addTerminalLine("  SESSÃO : DET.SOUZA — ativa desde 23:47", "term-line-system");
        addTerminalLine("  ERROS  : " + errorCount + " erro(s) nesta sessão", errorCount > 0 ? "term-line-warning" : "term-line-system");
        addBlankLine();
    }

    private void cmdCd(String path) {
        if (path.isEmpty()) {
            addTerminalLine("  Diretório atual: " + currentDirectory, "term-line-output");
            return;
        }
        if (path.equals("..")) {
            currentDirectory = "C:\\FORENSYS\\CASOS";
            currentPathLabel.setText(currentDirectory + "\\");
            addTerminalLine("  Navegando para: " + currentDirectory, "term-line-system");
        } else if (fileSystem.containsKey(path.toUpperCase())) {
            currentDirectory = "C:\\FORENSYS\\CASOS\\1994\\" + path.toUpperCase();
            currentPathLabel.setText(currentDirectory + "\\");
            addTerminalLine("  Navegando para: " + currentDirectory, "term-line-system");
        } else {
            addTerminalLine("  ERRO: Diretório não encontrado: " + path, "term-line-error");
        }
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

    @FXML
    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            if (!commandHistory.isEmpty() && historyIndex < commandHistory.size() - 1) {
                historyIndex++;
                commandInput.setText(commandHistory.get(historyIndex));
                commandInput.positionCaret(commandInput.getText().length());
            }
            event.consume();
        } else if (event.getCode() == KeyCode.DOWN) {
            if (historyIndex > 0) {
                historyIndex--;
                commandInput.setText(commandHistory.get(historyIndex));
            } else {
                historyIndex = -1;
                commandInput.clear();
            }
            commandInput.positionCaret(commandInput.getText().length());
            event.consume();
        } else if (event.getCode() == KeyCode.TAB) {
            // Autocomplete básico (futuro: implementar)
            event.consume();
        }
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

    private void updateErrorCount() {
        errorCountLabel.setText(String.valueOf(errorCount));
        if (errorCount > 0) {
            errorCountLabel.setStyle("-fx-text-fill: #FF3A3A;");
        }
    }

    private String findFile(String query) {
        String q = query.toLowerCase();
        for (List<String> files : fileSystem.values()) {
            for (String file : files) {
                if (file.toLowerCase().contains(q) || file.toLowerCase().equals(q)) {
                    return file;
                }
            }
        }
        return null;
    }
}
