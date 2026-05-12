package com.forensys.core.command;

import com.forensys.ui.command.ParsedCommandArgs;

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
    public abstract CommandOutput run(ParsedCommandArgs args);

}
