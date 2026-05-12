package com.forensys.core.command;

public record ParsedCommand(
    String command,
    ParsedCommandArgs arguments
) {}