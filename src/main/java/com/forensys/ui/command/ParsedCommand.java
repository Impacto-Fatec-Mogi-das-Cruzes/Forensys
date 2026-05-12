package com.forensys.ui.command;

public record ParsedCommand(
    String command,
    ParsedCommandArgs arguments
) {}