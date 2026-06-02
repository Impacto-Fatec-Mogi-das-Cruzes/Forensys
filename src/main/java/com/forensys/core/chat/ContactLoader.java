package com.forensys.core.chat;

import java.io.InputStream;

import com.forensys.common.loader.Loader;

public class ContactLoader extends Loader<InputStream> {

    private static ContactLoader instance;

    private ContactLoader() {
        super(new LoadingContact());
    }

    public static ContactLoader getInstance() {
        if (instance == null) {
            instance = new ContactLoader();
        }
        return instance;
    }

}
