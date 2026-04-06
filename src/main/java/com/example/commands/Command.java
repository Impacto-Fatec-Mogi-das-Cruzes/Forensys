package com.example.commands;

import java.util.List;

// Command Pattern
public interface Command {
    String execute(List<String> args);
}