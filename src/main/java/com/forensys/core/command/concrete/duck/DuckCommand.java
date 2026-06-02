package com.forensys.core.command.concrete.duck;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.forensys.common.HexColor;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;

public class DuckCommand extends TerminalCommand{

    public DuckCommand() {
        super(new CommandMetadata(
        "duck",
        "Display a duck",
        """
        Usage:
        duck

        Examples:
        duck

        Notes:
        - Shows a duck
        - The duck is harmless
        - No configuration required
        - No additional functionality is provided
        - It is, in fact, just a duck
        - That's it
        - Quack
        """));
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {        
        List<String> ARGUMENT_ERRORS = List.of(
                "The duck does not accept arguments. It is just a duck.",
                "The duck is confused by the extra arguments.",
                "Unexpected arguments. The duck has no configurable settings.",
                "Too many breadcrumbs. Just type 'duck'.",
                "Arguments are not supported. The duck arrives exactly as intended.",
                "You tried to customize the duck. The duck disagrees.",
                "The duck acknowledges your arguments and ignores them.",
                "This command only displays a duck. A very opinionated duck.",
                "Error: duck.exe has stopped listening, and caring.",
                "The duck requests fewer words and more quacking."
        );

        if (!args.positionals().isEmpty() || !args.flags().isEmpty() || !args.options().isEmpty()) {
            String message = ARGUMENT_ERRORS.get(
                    ThreadLocalRandom.current().nextInt(ARGUMENT_ERRORS.size())
            );

            return CommandOutput.builder()
                    .styledText(message, "#ef4444")
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        }

        if (ThreadLocalRandom.current().nextInt(100000) == 0) {
            int roll = ThreadLocalRandom.current().nextInt(10);

            HexColor color = switch (roll) {
                case 0, 1, 2, 3, 4, 5 -> HexColor.of("#3b82f6"); // blue
                case 6, 7, 8 -> HexColor.of("#22c55e");          // green
                default -> HexColor.of("#ec4899");               // pink
            };

            return CommandOutput.builder()
                .styledText("  i______i", color.value()).newLine()
                .styledText("  I______I ", color.value()).newLine()
                .styledText("  I      I", color.value()).newLine()
                .styledText("  I______I", color.value()).newLine()
                .styledText(" /      /I", color.value()).newLine()
                .styledText("(______( I", color.value()).newLine()
                .styledText("I \"    I \"", color.value()).newLine()
                .styledText("I      I", color.value()).newLine()
                .styledText("...", color.value()).newLine()
                .exitCode(CommandExitCode.SUCCESS)
                .build();
        }
        
        return CommandOutput.builder()
            .styledText("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡶⠿⠿⠷⣶⣄⠀⠀⠀⠀⠀", "#cbd5e1").newLine()
            .styledText("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠁⠀⠀⢀⣀⡀⠙⣷⡀⠀⠀⠀", "#cbd5e1").newLine()
            .styledText("⠀⠀⠀⡀⠀⠀⠀⠀⠀⢠⣿⠁⠀⠀⠀⠘⠿⠃⠀⢸⣿⣿⣿⣿   Quack", "#cbd5e1").newLine()
            .styledText("⠀⣠⡿⠛⢷⣦⡀⠀⠀⠈⣿⡄⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⠟", "#cbd5e1").newLine()
            .styledText("⢰⡿⠁⠀⠀⠙⢿⣦⣤⣤⣼⣿⣄⠀⠀⠀⠀⠀⢴⡟⠛⠋⠁⠀", "#cbd5e1").newLine()
            .styledText("⣿⠇⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠈⣿⡀⠀⠀⠀", "#cbd5e1").newLine()
            .styledText("⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡇⠀⠀⠀", "#cbd5e1").newLine()
            .styledText("⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡇⠀⠀⠀", "#cbd5e1").newLine()
            .styledText("⠸⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠀⠀⠀⠀", "#cbd5e1").newLine()
            .styledText("⠀⠹⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣰⡿⠁⠀⠀⠀⠀", "#cbd5e1").newLine()
            .styledText("⠀⠀⠀⠉⠙⠛⠿⠶⣶⣶⣶⣶⣶⠶⠿⠟⠛⠉⠀⠀⠀⠀⠀⠀", "#cbd5e1").newLine()
            .exitCode(CommandExitCode.SUCCESS)
            .build();
    }
}
