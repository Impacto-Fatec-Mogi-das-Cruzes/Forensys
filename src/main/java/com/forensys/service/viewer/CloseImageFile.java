package com.forensys.service.viewer;

import com.forensys.core.context.ApplicationContext;

public class CloseImageFile {
    public static void execute() {
        ApplicationContext.getInstance().closeImage();
    }
}
