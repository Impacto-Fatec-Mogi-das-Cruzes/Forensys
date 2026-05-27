package com.forensys.core.command.concrete.go;

import com.forensys.core.command.concrete.go.strategy.GoParentStrategy;
import com.forensys.core.command.concrete.go.strategy.GoRootStrategy;
import com.forensys.core.command.concrete.go.strategy.GoStrategy;
import com.forensys.core.command.concrete.go.strategy.GoDirectory.GoDirectoryStrategy;

// TODO: resolver -> factory
public class GoStrategyResolver {
    public GoStrategy resolve(String target) {

        if (target.equals("$root")) {
            return new GoRootStrategy();
        }

        if (target.equals("$parent")) {
            return new GoParentStrategy();
        }

        return new GoDirectoryStrategy(target);
    }
}