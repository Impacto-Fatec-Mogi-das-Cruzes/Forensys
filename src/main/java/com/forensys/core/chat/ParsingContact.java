package com.forensys.core.chat;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forensys.common.parser.ParsingStrategy;

public class ParsingContact implements ParsingStrategy<Contact> {

    @Override
    public Contact parse(String resource) {
        ObjectMapper mapper = new ObjectMapper();
        Contact data = null;
        try {
            InputStream is = ContactLoader.getInstance().load(resource);
            data = mapper.readValue(is, Contact.class);
        } catch (Exception e) {
            System.err.println(e);
        }

        return data;
    }

}
