package com.forensys.common.parser;

public abstract class Parser<T> {
    private final ParsingStrategy<T> parseStrategy;

    protected Parser(ParsingStrategy<T> parseStrategy) {
        this.parseStrategy = parseStrategy;
    }

    public T parse(String resourceFile) {
        return getParseStrategy().parse(resourceFile);
    };

    protected ParsingStrategy<T> getParseStrategy() {
        return this.parseStrategy;
    }
}
