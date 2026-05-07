package com.forensys.core.chat;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forensys.common.parser.ParsingStrategy;

public class ParsingChat implements ParsingStrategy<ContactList> {

    @Override
    public ContactList parse(String resource) {
        ObjectMapper mapper = new ObjectMapper();
        ContactList data = null;
        try {
            InputStream is = ChatLoader.getInstance().load(resource);
            data = mapper.readValue(is, ContactList.class);
        } catch (Exception e) {
            System.err.println(e);
        }

        return data;
    }

}
