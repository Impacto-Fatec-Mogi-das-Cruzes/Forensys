package com.forensys.ui.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsedCommandBuilder {
    private String command;
    private List<String> flags;
    private Map<String, String> options;
    private List<String> args;

    public ParsedCommandBuilder() {
        this.command = "";
        this.flags = new ArrayList<>();
        this.options = new HashMap<>();
        this.args = new ArrayList<>();
    }

    public ParsedCommandBuilder command(String command) {
        this.command = command;
        return this;
    }

    public ParsedCommandBuilder flag(String raw) {
        if (raw.startsWith("-")) {
            flags.addAll(Arrays.asList(raw.replace("-", "").split("")));
        } else {
            throw new IllegalArgumentException("Flag must start with a '-'");
        }
        return this;
    }

    public ParsedCommandBuilder option(String raw) {
        if (raw.startsWith("-")) {
            raw = raw.replace("-", "");
        } else {
            throw new IllegalArgumentException("Option must start with a '-'");
        }

        options.put(raw.split("=", 2)[0], raw.split("=", 2)[1]);
        return this;
    }

    public ParsedCommandBuilder argument(String raw) {
        args.add(raw);
        return this;
    }

    public ParsedCommand build() {
        return new ParsedCommand(getCommand(), new ParsedCommandArgs(getFlags(), getOptions(), getArgs()));
    }

    private String getCommand() {
        return command;
    }

    private List<String> getFlags() {
        return new ArrayList<>(flags);
    }

    private Map<String, String> getOptions() {
        return new HashMap<>(options);
    }

    private List<String> getArgs() {
        return new ArrayList<>(args);
    }
}
