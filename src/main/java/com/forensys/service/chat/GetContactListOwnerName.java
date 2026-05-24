package com.forensys.service.chat;

import com.forensys.core.chat.ContactList;

public class GetContactListOwnerName {

    public static String execute(ContactList contactList) {

        return contactList.getOwner()
                .getName();
    }
}