package com.example.ui.parser;

import java.util.List;

public record ParsedCommand(
    String command,
    List<String> args
) {}
