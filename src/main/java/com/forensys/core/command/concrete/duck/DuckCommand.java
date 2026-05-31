package com.forensys.core.command.concrete.duck;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
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
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

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

            return outputBuilder
                    .styledText(message, "#ef4444")
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        }
        
        outputBuilder
            .text("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡶⠿⠿⠷⣶⣄⠀⠀⠀⠀⠀").newLine()
            .text("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠁⠀⠀⢀⣀⡀⠙⣷⡀⠀⠀⠀").newLine()
            .text("⠀⠀⠀⡀⠀⠀⠀⠀⠀⢠⣿⠁⠀⠀⠀⠘⠿⠃⠀⢸⣿⣿⣿⣿   Quack").newLine()
            .text("⠀⣠⡿⠛⢷⣦⡀⠀⠀⠈⣿⡄⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⠟").newLine()
            .text("⢰⡿⠁⠀⠀⠙⢿⣦⣤⣤⣼⣿⣄⠀⠀⠀⠀⠀⢴⡟⠛⠋⠁⠀").newLine()
            .text("⣿⠇⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠈⣿⡀⠀⠀⠀").newLine()
            .text("⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡇⠀⠀⠀").newLine()
            .text("⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡇⠀⠀⠀").newLine()
            .text("⠸⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠀⠀⠀⠀").newLine()
            .text("⠀⠹⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣰⡿⠁⠀⠀⠀⠀").newLine()
            .text("⠀⠀⠀⠉⠙⠛⠿⠶⣶⣶⣶⣶⣶⠶⠿⠟⠛⠉⠀⠀⠀⠀⠀⠀").newLine()
            .exitCode(CommandExitCode.SUCCESS);
        
        return outputBuilder.build();
    }
}
