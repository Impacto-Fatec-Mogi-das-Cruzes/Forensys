package com.forensys.core.command.concrete.view;

import java.util.Optional;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.ImageFile;

public class ViewCommand extends TerminalCommand {

    public ViewCommand() {
        super(new CommandMetadata(
            "view",
            "view a imame",
            "imge"
        ));
    }

    @Override
    public CommandOutput execute(ParsedCommandArgs args) {
        ApplicationContext context = ApplicationContext.getInstance();
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        if (args.positionals().isEmpty()) {
            return outputBuilder
                    .text("No arguments passed, command requires a argument")
                    .exitCode(CommandExitCode.FAILURE)
                    .build();
        }

        String target = args.positionals().getFirst();

        Optional<FileSystemEntry> entry = context.getCurrentDirectory()
                .getChildren()
                .stream()
                .filter(obj -> obj.getMetadata().name().equals(target))
                .findFirst();

        if (entry.isEmpty() || !(entry.get() instanceof ImageFile imageFile)) {
            outputBuilder
                    .text("File not found, please choose a valid file")
                    .exitCode(CommandExitCode.FAILURE);
        } else {
            context.openImage(imageFile);
            outputBuilder
                    .text("Opening image viewer " + target)
                    .exitCode(CommandExitCode.SUCCESS);
        }

        return outputBuilder.build();
    }

}
