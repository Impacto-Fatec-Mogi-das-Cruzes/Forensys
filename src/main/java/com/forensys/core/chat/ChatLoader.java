package com.forensys.core.chat;

import java.io.InputStream;

import com.forensys.common.loader.Loader;

public class ChatLoader extends Loader<InputStream> {

    private static ChatLoader instance;

    private ChatLoader() {
        super(new LoadingChat());
    }

    public static ChatLoader getInstance() {
        if (instance == null) {
            instance = new ChatLoader();
        }
        return instance;
    }

}
