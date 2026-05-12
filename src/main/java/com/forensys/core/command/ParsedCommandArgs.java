package com.forensys.core.command;

import java.util.List;
import java.util.Map;

public record ParsedCommandArgs(
    List<String> flags,
    Map<String, String> options,
    List<String> positionals
) {}
