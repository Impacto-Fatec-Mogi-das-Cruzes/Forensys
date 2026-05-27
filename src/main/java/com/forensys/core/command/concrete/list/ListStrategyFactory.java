package com.forensys.core.command.concrete.list;

import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.concrete.list.decorator.BaseEntryFilter;
import com.forensys.core.command.concrete.list.decorator.EntryFilter;
import com.forensys.core.command.concrete.list.decorator.HiddenEntryFilterDecorator;
import com.forensys.core.command.concrete.list.strategy.EntryRenderStrategy;
import com.forensys.core.command.concrete.list.strategy.LongEntryRenderStrategy;
import com.forensys.core.command.concrete.list.strategy.SimpleEntryRenderStrategy;

public class ListStrategyFactory {

    public static EntryRenderStrategy createRenderStrategy(ParsedCommandArgs args) {
        if (args.flags().contains("l")) {
            return new LongEntryRenderStrategy();
        }

        return new SimpleEntryRenderStrategy();
    }

    public static EntryFilter createFilter(ParsedCommandArgs args) {
        EntryFilter filter = new BaseEntryFilter();

        if (!args.flags().contains("a")) {
            filter = new HiddenEntryFilterDecorator(filter);
        }

        return filter;
    }
}