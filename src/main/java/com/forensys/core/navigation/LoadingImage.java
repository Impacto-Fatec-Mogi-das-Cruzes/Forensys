package com.forensys.core.navigation;

import com.forensys.common.loader.LoadingStrategy;

import javafx.scene.image.Image;

public class LoadingImage implements LoadingStrategy<Image>{

    @Override
    public Image load(String filePath) {
        Image image = null;
        try {
            image = new Image(getClass().getResourceAsStream("/assets/filestructure/" + filePath));
        } catch (Exception e) {
            System.err.println(e);
        }

        if (image == null) {
            throw new RuntimeException("File not found");
        }

        return image;
    }

}
