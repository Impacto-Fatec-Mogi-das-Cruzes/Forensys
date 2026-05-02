package com.forensys.core.setting;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forensys.common.parser.ParsingStrategy;

// Settings Parsing Strategy
public class ParsingSettings implements ParsingStrategy<Settings>{
    @Override
    public Settings parse(String resource) {
        Settings data = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = SettingsLoader.getInstance().load(resource);
            data = mapper.readValue(is, Settings.class);
        } catch (Exception e) {
            System.err.println(e);
        }
        return data;
    }

}
