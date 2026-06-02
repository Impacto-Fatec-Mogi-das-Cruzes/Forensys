package com.forensys.core.navigation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.forensys.common.loader.LoadingStrategy;

public class LoadingFile implements LoadingStrategy<String>{

    @Override
    public String load(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/assets/filestructure/" + filePath);
            if (inputStream == null) {
                return null;
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
