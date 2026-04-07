package com.example.commands;

import java.util.List;

public abstract class TerminalCommand {

    private final String commandName;
    private final String helpMessageBrief;
    private final String helpMessageLong;
    private String output;

    public TerminalCommand(String commandName, String helpMessageBrief, String helpMessageLong) {
        this.commandName = commandName;
        this.helpMessageBrief = helpMessageBrief;
        this.helpMessageLong = helpMessageLong;
    }
    
    public String getCommandName(){
        return this.commandName;
    }
    
    public String getHelpMessageBrief(){
        return this.helpMessageBrief;
    }

    public String getHelpMessageLong(){
        return this.helpMessageLong;
    }
    
    public String getOutput() {
        return this.output;
    }
    
    public void setOutput(String output) {
        this.output = output;
    }

    public abstract void run(List<String> args);
}
