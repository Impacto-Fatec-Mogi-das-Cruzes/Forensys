package com.forensys.core.navigation;

import com.forensys.common.loader.Loader;

import javafx.scene.image.Image;

public class ImageLoader extends Loader<Image>{
    
    private static ImageLoader instance;

    private ImageLoader() {
        super(new LoadingImage());
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }
}
