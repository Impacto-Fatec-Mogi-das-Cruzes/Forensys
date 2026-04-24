package com.forensys.common.parser;

public abstract class Parser<T> {
    private final ParsingStrategy<T> parseStrategy;

    protected Parser(ParsingStrategy<T> parseStrategy) {
        this.parseStrategy = parseStrategy;
    }

    protected abstract T parse(String resourceFile);

    protected ParsingStrategy<T> getParseStrategy() {
        return this.parseStrategy;
    }
}
