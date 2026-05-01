package com.forensys.core.command;

public record CommandMetadata(
    String commandName,
    String helpMessage,
    String description
) {}
