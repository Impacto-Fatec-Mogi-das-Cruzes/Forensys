package com.forensys.service.terminal;

import com.forensys.core.command.CommandParser;
import com.forensys.core.command.ParsedCommand;

public class ParseCommand {
    public static ParsedCommand execute(String rawInput) {
        return CommandParser.getInstance().parse(rawInput);
    }
}
