package com.example.ui.command;

import java.util.List;

public record ParsedCommand(
    String command,
    List<String> args
) {}
