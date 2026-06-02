package com.forensys.service.chat;

import com.forensys.core.chat.ContactList;
import com.forensys.core.context.ApplicationContext;

public class GetContactList {

    public static ContactList execute() {
        return ApplicationContext.getInstance()
                .getContactList();
    }
}