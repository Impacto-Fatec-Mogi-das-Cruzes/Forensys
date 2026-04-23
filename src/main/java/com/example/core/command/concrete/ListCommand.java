package com.example.core.command.concrete;

import java.util.List;
import java.util.Map;

import com.example.core.SystemContext;
import com.example.core.command.CommandExitCode;
import com.example.core.command.CommandMetadata;
import com.example.core.command.CommandOutput;
import com.example.core.command.CommandOutputBuilder;
import com.example.core.command.TerminalCommand;
import com.example.core.filestructure.FileSystemEntry;
import com.example.core.filestructure.concrete.Directory;

public class ListCommand extends TerminalCommand {

    public ListCommand() {
        super(new CommandMetadata("list", "list all the children entries in the dir", "bla bla bla, flags flags and more flags"));
    }

    @Override
    public CommandOutput run(List<String> args) {
        SystemContext context = SystemContext.getInstance();
        CommandOutputBuilder outputBuilder = new CommandOutputBuilder();

        outputBuilder.text("In the current directory you have:");
        for (Map.Entry<String,FileSystemEntry> entry : context.getCurrentDirectory().getChildren().entrySet()) {
            if (entry.getValue() instanceof Directory) {
                outputBuilder.text("\t" + entry.getKey() + "/");
            } else {
                outputBuilder.text("\t" + entry.getKey());
            }
        }

        return outputBuilder.exitCode(CommandExitCode.SUCESS).build();
    }

}
