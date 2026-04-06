package com.example.commands;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

// Singleton pattern
public class CommandParser {
    private static CommandParser instance;

    private CommandParser(){}

    public static CommandParser getInstance() {
        if (CommandParser.instance == null) {
            instance = new CommandParser();
        }
        return instance;
    }

    public ParsedCommand parse(String rawInput) {
        String command;
        List<String> args = new ArrayList<>(Arrays.asList(rawInput.split("\\s+")));

        command = args.getFirst();
        args.remove(command);

        return new ParsedCommand(command, args);
    }
}
