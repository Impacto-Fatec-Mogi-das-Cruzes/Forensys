package com.forensys.core.command.concrete.go;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.Directory;

public class GoCommand extends TerminalCommand {

    public GoCommand() {
        super(new CommandMetadata("go", "Changes the current directory",
                "hint: you can use back, if you want to go back"));
    }

    @Override
    public CommandOutput run(ParsedCommandArgs args) {
        ApplicationContext context = ApplicationContext.getInstance();
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        if (!validateArgs(args, outputBuilder)) {
            return outputBuilder.build();
        }

        String target = args.positionals().get(0);

        if ("back".equals(target)) {
            try {
                context.restoreDirectory();
                outputBuilder
                        .text("Back to directory " + context.getCurrentDirectory().getMetadata().name())
                        .exitCode(CommandExitCode.SUCCESS);
            } catch (NoSuchElementException e) {
                outputBuilder
                        .text("No parent directory to go back")
                        .exitCode(CommandExitCode.FAILURE);
            }
            return outputBuilder.build();
        }

        Optional<FileSystemEntry> entry = context.getCurrentDirectory()
                .getChildren()
                .stream()
                .filter(obj -> obj.getMetadata().name().equals(target))
                .findFirst();

        if (entry.isEmpty() || !(entry.get() instanceof Directory directory)) {
            outputBuilder
                    .text("Directory not found, please choose a valid directory")
                    .exitCode(CommandExitCode.FAILURE);
        } else {
            context.setCurrentDirectory(directory);
            outputBuilder
                    .text("Current location whas changed to  " + target)
                    .exitCode(CommandExitCode.SUCCESS);
        }

        return outputBuilder.build();
    }

    private boolean validateArgs(ParsedCommandArgs args, CommandOutputBuilder outputBuilder) {
        if (args.positionals().isEmpty()) {
            outputBuilder
                    .text("No arguments passed, command requires a argument")
                    .exitCode(CommandExitCode.FAILURE);
            return false;
        }

        if (args.positionals().size() > 1) {
            outputBuilder
                    .text("Too many arguments passed for command go")
                    .exitCode(CommandExitCode.FAILURE);
            return false;
        }

        return true;
    }
}
