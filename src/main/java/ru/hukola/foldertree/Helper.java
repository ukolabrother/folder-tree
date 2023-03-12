package ru.hukola.foldertree;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Helper {
    public static Node newFolderImage() {
        ImageView image = new ImageView("folder.png");
        image.setFitWidth(20);
        image.setFitHeight(20);
        return image;
    }
}
