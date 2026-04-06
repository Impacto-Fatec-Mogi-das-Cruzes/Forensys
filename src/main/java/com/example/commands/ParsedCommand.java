package com.example.commands;

import java.util.List;

public record ParsedCommand(
    String command,
    List<String> args
) {}
