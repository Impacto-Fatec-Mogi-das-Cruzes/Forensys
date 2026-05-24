package com.forensys.service.reader;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.concrete.TextFile;

public class GetTextFile {
    public static TextFile execute() {
        return ApplicationContext.getInstance().getTextFile();
    }
}
