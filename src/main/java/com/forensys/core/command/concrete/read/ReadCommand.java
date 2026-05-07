package com.forensys.core.command.concrete.read;

import java.util.List;
import java.util.Optional;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.TextFile;

public class ReadCommand extends TerminalCommand {

    public ReadCommand() {
        super(new CommandMetadata("read", "Read a fiel", "Read a file in the directory"));
    }

    @Override
    public CommandOutput run(List<String> args) {
        ApplicationContext context = ApplicationContext.getInstance();
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        if (args.isEmpty()) {
            return outputBuilder
                    .text("No arguments passed, command requires a argument")
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        }

        String target = args.getFirst();

        Optional<FileSystemEntry> entry = context.getCurrentDirectory()
                .getChildren()
                .stream()
                .filter(obj -> obj.getMetadata().name().equals(target))
                .findFirst();

        if (entry.isEmpty() || !(entry.get() instanceof TextFile textFile)) {
            outputBuilder
                    .text("File not found, please choose a valid file")
                    .exitCode(CommandExitCode.FAILURE);
        } else {
            context.openFile(textFile);
            outputBuilder
                    .text("Opening file reader " + target)
                    .exitCode(CommandExitCode.SUCCESS);
        }

        return outputBuilder.build();

    }

}
