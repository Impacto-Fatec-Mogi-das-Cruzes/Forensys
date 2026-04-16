package com.example.parsers.strategies;

import java.io.InputStream;
import com.example.gamestate.settings.Settings;
import com.fasterxml.jackson.databind.ObjectMapper;

// Settings Parsing Strategy
public class ParsingSettings implements ParsingStrategy<Settings>{
    @Override
    public Settings parse(String resource) {
        Settings data = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = getClass().getResourceAsStream("/config/" + resource);
            if (is == null) {
               throw new RuntimeException("File not found");
            }
            data = mapper.readValue(is, Settings.class);
        } catch (Exception e) {
            System.err.println(e);
        }
        return data;
    }

}
