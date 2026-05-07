package com.forensys.core.chat;

import com.forensys.common.parser.Parser;

public class ChatParser extends Parser<ContactList> {

    private static ChatParser instance;

    private ChatParser() {
        super(new ParsingChat());
    }

    public static ChatParser getInstance() {
        if (instance == null) {
            instance = new ChatParser();
        }
        return instance;
    }

}
