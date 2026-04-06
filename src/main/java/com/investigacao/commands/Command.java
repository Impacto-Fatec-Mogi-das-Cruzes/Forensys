package com.investigacao.commands;

import java.util.List;

// Command Pattern
public interface Command {
    String execute(List<String> args);
}