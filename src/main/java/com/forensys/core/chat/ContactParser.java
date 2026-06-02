package com.forensys.core.chat;

import com.forensys.common.parser.Parser;

public class ContactParser extends Parser<Contact> {

    private static ContactParser instance;

    private ContactParser() {
        super(new ParsingContact());
    }

    public static ContactParser getInstance() {
        if (instance == null) {
            instance = new ContactParser();
        }
        return instance;
    }

}
