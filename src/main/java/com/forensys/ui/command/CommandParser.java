package com.forensys.ui.command;

import com.forensys.common.parser.Parser;

public class CommandParser extends Parser<ParsedCommand> {
    private static CommandParser instance;

    private CommandParser() {
        super(new ParsingCommand());
    }

    public static CommandParser getInstance() {
        if (instance == null) {
            instance = new CommandParser();
        }
        return instance;
    }
    
}
