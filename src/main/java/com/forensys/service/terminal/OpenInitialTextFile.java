package com.forensys.service.terminal;

import com.forensys.core.context.ApplicationContext;
import com.forensys.core.filestructure.FileMetadata;
import com.forensys.core.filestructure.concrete.TextFile;

public class OpenInitialTextFile {
    public static void execute() {
        TextFile initialTextFile = new TextFile();
        initialTextFile.setType("text");
        initialTextFile.setMetadata(new FileMetadata("report.txt", null, false, false, "a", 0, "never", "ever", true));
        initialTextFile.setContent("InitialTextFile.txt");
        ApplicationContext.getInstance().openFile(initialTextFile);
    }
}
