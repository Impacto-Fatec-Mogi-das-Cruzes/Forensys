package com.forensys.ui.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.forensys.common.parser.ParsingStrategy;

// Command Parsing Strategy
public class ParsingCommand implements ParsingStrategy<ParsedCommand> {
    @Override
    public ParsedCommand parse(String rawInput) {
        ParsedCommandBuilder commandBuilder = new ParsedCommandBuilder();

        List<String> tokens = new ArrayList<>(Arrays.asList(rawInput.trim().split("\\s+")));
        commandBuilder.command(tokens.remove(0));

        for (String token : tokens) {
            if (!token.startsWith("-")) {
                commandBuilder.argument(token);
            } else if (token.contains("=")) {
                commandBuilder.option(token);
            } else {
                commandBuilder.flag(token);
            }
        }

        return commandBuilder.build();
    }

}
