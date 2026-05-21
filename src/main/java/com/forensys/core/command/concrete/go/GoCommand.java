package com.forensys.core.command.concrete.go;

import com.forensys.common.command.ExecutionStrategy;
import com.forensys.common.command.StrategyResolver;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;

public class GoCommand extends TerminalCommand {

    private final StrategyResolver resolver;

    public GoCommand() {
        super(
            new CommandMetadata(
                "go",
                "Navigate between directories in the virtual file system",
                """
                Usage:
                go <directory>
                go <path>
                go $parent
                go $root

                Examples:
                go documents
                go $parent/projects
                go $root/downloads
                go $parent

                Notes:
                - Relative and absolute paths are supported
                - Use $parent to return to the previous directory
                """
            ));
        this.resolver = new GoStrategyResolver();
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        try {
            ExecutionStrategy strategy = resolver.resolve(args);
            return strategy.execute(args);
        } catch (Exception e) {
            return CommandOutput.builder()
                    .text(e.getMessage())
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        }
    }
}
