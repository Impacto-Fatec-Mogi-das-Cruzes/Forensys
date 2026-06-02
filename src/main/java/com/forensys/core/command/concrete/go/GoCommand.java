package com.forensys.core.command.concrete.go;

import java.util.List;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.go.strategy.GoStrategy;
import com.forensys.core.context.ApplicationContext;

public class GoCommand extends TerminalCommand {
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
                        """));
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        if (args.positionals().size() < 1) {
            return CommandOutput.builder()
                .styledText("Too few arguments passed for 'go' command", "#ef4444")
                .exitCode(CommandExitCode.FAILURE)
                .build();
        }
        ApplicationContext applicationContext = ApplicationContext.getInstance();
        if (applicationContext.getPendingExecution() == null) {
            applicationContext.setPendingExecution(() -> {
                return this.execute(args);
            });
        }
        
        GoExecutionContext state = (GoExecutionContext) ApplicationContext.getInstance().getExecutionContext();
        if (state == null) {
            state = new GoExecutionContext(
                List.of(args.positionals().getFirst().split("/")), 
                0);
            applicationContext.setExecutionContext(state);
        }

        CommandOutput output = null;

        while (state.hasTargetsLeft()) {
            String target = state.getCurrentTarget();
            GoStrategy strategy = GoStrategyFactory.creatStrategy(target);
            output = strategy.execute();
            
            if (output.getExitCode() == CommandExitCode.PAUSE || output.getExitCode() == CommandExitCode.FAILURE) {
                return output;
            }
        }

        applicationContext.clearAllExecution();
        return output;
    }
}