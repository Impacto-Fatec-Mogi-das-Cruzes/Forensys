package com.forensys.core.command.concrete.list;

import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.Directory;

// TODO: Apply a decorator pattern to the behaviors of listing with metadata and/or hidden
public class ListCommand extends TerminalCommand {

    public ListCommand() {
        super(new CommandMetadata("list", "list all the children entries in the dir", "bla bla bla, flags flags and more flags"));
    }

    @Override
    public CommandOutput run(ParsedCommandArgs args) {
        ApplicationContext context = ApplicationContext.getInstance();
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        outputBuilder.text("In the current directory you have:").newLine();
        for (FileSystemEntry entry : context.getCurrentDirectory().getChildren()) {
            if (entry instanceof Directory) {
                outputBuilder.text("\t" + entry.getMetadata().name() + "/");
            } else {
                outputBuilder.text("\t" + entry.getMetadata().name());
            }
            outputBuilder.newLine();
        }

        return outputBuilder.exitCode(CommandExitCode.SUCCESS).build();
    }

}
