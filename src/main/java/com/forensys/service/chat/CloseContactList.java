package com.forensys.service.chat;

import com.forensys.core.context.ApplicationContext;

public class CloseContactList {

    public static void execute() {
        ApplicationContext.getInstance()
                .closeContactList();
    }
}