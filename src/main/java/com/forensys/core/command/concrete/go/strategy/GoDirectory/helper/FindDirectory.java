package com.forensys.core.command.concrete.go.strategy.GoDirectory.helper;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.Directory;

public class FindDirectory {
    public static Directory execute(String target) {
        for (Directory child : ApplicationContext.getInstance().getCurrentDirectory().getDirectories()) {
            if (child.getMetadata().name().equals(target)) {
                return child;
            }
        }
        return null;
    }
}
