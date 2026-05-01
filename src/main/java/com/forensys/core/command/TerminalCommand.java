package com.forensys.core.command;

import java.util.List;

public abstract class TerminalCommand {

    private final CommandMetadata metadata;

    protected TerminalCommand(CommandMetadata metadata) {
        this.metadata = metadata;
    }
    
    public String getCommandName(){
        return this.metadata.commandName();
    }
    
    public String getHelpMessage(){
        return this.metadata.helpMessage();
    }

    public String getDescription(){
        return this.metadata.description();
    }

    // TODO: find a clean way to deal with args
    public abstract CommandOutput run(List<String> args);

}
