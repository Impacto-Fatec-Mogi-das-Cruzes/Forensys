package com.forensys.service.reader;

import com.forensys.core.context.ApplicationContext;

public class CloseTextFile {
    public static void execute() {
        ApplicationContext.getInstance().closeFile();
    }
}
