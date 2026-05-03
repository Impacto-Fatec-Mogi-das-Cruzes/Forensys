package com.forensys.ui.navigation;

import com.forensys.common.loader.LoadingStrategy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoadingInterface implements LoadingStrategy<Parent> {

    @Override
    public Parent load(String sourceData) {
        Parent root = null;

        try {
            root = new FXMLLoader(getClass().getResource("/interfaces/" + sourceData + ".fxml")).load();
        } catch (Exception e) {
            System.err.println(e);
        }

        if (root == null) {
            throw new RuntimeException("File not found");
        }

        return root;
    }

}
