package com.forensys.core.command.concrete.go;

import com.forensys.common.command.ExecutionStrategy;
import com.forensys.common.exception.InvalidArgumentCountException;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.command.concrete.go.strategy.GoBackStrategy;
import com.forensys.core.command.concrete.go.strategy.GoToStrategy;

public class GoCommand extends TerminalCommand {

    private ExecutionStrategy strategy;

    public GoCommand() {
        super(
            new CommandMetadata(
                "go",
                "Changes the current directory",
                "hint: you can use back, if you want to go back"));
    }

    @Override
    public CommandOutput run(ParsedCommandArgs args) {

        try {
            if (args.flags().isEmpty() && args.options().isEmpty() && args.positionals().isEmpty()) {
                throw new InvalidArgumentCountException("No flag, option or positional argument passed");
            }
        } catch (InvalidArgumentCountException e) {
            return CommandOutput.builder()
                    .text(e.getMessage())
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        } catch (Exception e) {
            System.err.println(e);
        }

        if (args.flags().contains("b")) {
            strategy = new GoBackStrategy();
        } else if (!args.positionals().isEmpty()) {
            strategy = new GoToStrategy();
        } else {

        }
        return strategy.execute(args);
    }
}
