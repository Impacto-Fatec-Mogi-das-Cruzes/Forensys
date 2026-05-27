package com.forensys.core.command.concrete.list.decorator;

public abstract class EntryFilterDecorator implements EntryFilter {

    protected final EntryFilter delegate;

    protected EntryFilterDecorator(EntryFilter delegate) {
        this.delegate = delegate;
    }
}