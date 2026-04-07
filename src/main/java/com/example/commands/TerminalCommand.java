package com.example.commands;

import java.util.List;

public abstract class TerminalCommand {

    private final String commandName;
    private final String helpMessage;
    private String output;

    public TerminalCommand(String commandName, String helpMessage) {
        this.commandName = commandName;
        this.helpMessage = helpMessage;
    }
    
    public String getCommandName(){
        return this.commandName;
    }
    
    public String getHelpMessage(){
        return this.helpMessage;
    }
    
    public String getOutput() {
        return this.output;
    }
    
    public void setOutput(String output) {
        this.output = output;
    }

    public abstract void run(List<String> args);
}
