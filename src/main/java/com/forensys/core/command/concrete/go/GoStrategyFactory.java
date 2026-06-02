package com.forensys.core.command.concrete.go;

import com.forensys.core.command.concrete.go.strategy.GoDirectoryStrategy;
import com.forensys.core.command.concrete.go.strategy.GoParentStrategy;
import com.forensys.core.command.concrete.go.strategy.GoRootStrategy;
import com.forensys.core.command.concrete.go.strategy.GoStrategy;

public class GoStrategyFactory {
    public static GoStrategy creatStrategy(String target) {

        if (target.equals("$root")) {
            return new GoRootStrategy();
        }

        if (target.equals("$parent")) {
            return new GoParentStrategy();
        }

        return new GoDirectoryStrategy(target);
    }
}