package com.forensys.common.parser;

public interface ParsingStrategy<T> {
    T parse(String sourceData);
}