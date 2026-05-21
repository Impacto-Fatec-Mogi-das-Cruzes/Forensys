package com.forensys.core.command.concrete.go;

import com.forensys.common.command.ExecutionStrategy;
import com.forensys.common.command.StrategyResolver;
import com.forensys.core.command.ParsedCommandArgs;
import com.forensys.core.command.concrete.go.strategy.GoToStrategy;

public class GoStrategyResolver implements StrategyResolver {

    @Override
    public ExecutionStrategy resolve(ParsedCommandArgs args) {

        // String target = args.positionals().getFirst();

        // if (target.equals("$root")) {
        //     return new GoRootStrategy();
        // }

        // if (target.equals("$parent")) {
        //     return new GoBackStrategy();   
        // }
        
        return new GoToStrategy();
    }
}