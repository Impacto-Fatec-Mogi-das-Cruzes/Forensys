package com.example.ui.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.common.parser.ParsingStrategy;

// Command Parsing Strategy
public class ParsingCommand implements ParsingStrategy<ParsedCommand>{
    @Override
    public ParsedCommand parse(String rawInput) {
        String command;
        List<String> args = new ArrayList<>(Arrays.asList(rawInput.split("\\s+")));

        command = args.getFirst();
        args.remove(command);

        return new ParsedCommand(command, args);
    }

}
