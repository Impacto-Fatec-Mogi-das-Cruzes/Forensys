package com.forensys.service.viewer;

import com.forensys.core.navigation.ImageLoader;

import javafx.scene.image.Image;

public class LoadImage {
    public static Image execute(String path) {
        return ImageLoader.getInstance().load(path);
    }
}
