package com.example.ui.parser;

import com.example.common.parser.Parser;

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

    @Override
    public ParsedCommand parse(String rawInput) {
        return getParseStrategy().parse(rawInput);
    }
}
