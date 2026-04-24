package com.forensys.core.command.concrete;

import java.util.List;

import com.forensys.core.ApplicationContext;
import com.forensys.core.command.CommandExitCode;
import com.forensys.core.command.CommandMetadata;
import com.forensys.core.command.CommandOutput;
import com.forensys.core.command.CommandOutputBuilder;
import com.forensys.core.command.TerminalCommand;
import com.forensys.core.filestructure.FileSystemEntry;
import com.forensys.core.filestructure.concrete.Directory;

public class ListCommand extends TerminalCommand {

    public ListCommand() {
        super(new CommandMetadata("list", "list all the children entries in the dir", "bla bla bla, flags flags and more flags"));
    }

    @Override
    public CommandOutput run(List<String> args) {
        ApplicationContext context = ApplicationContext.getInstance();
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        outputBuilder.text("In the current directory you have:");
        for (FileSystemEntry entry : context.getCurrentDirectory().getChildren()) {
            if (entry instanceof Directory) {
                outputBuilder.text("\t" + entry.getMetadata().getName() + "/");
            } else {
                outputBuilder.text("\t" + entry.getMetadata().getName());
            }
        }

        return outputBuilder.exitCode(CommandExitCode.SUCESS).build();
    }

}
