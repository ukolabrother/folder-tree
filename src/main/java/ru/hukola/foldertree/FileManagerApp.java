package ru.hukola.foldertree;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FileManagerApp extends Application {
    @Override
    public void start(Stage stage) {
        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
        int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();

        TreeView<String> treeView = new TreeView<>();
        treeView.setPrefWidth(screenWidth / 5);
        treeView.setPrefHeight(screenHeight * 90 / 100);

        TextField inputFolder = new TextField();
        inputFolder.setOnAction(e -> {
            fillTree(inputFolder.getText(), treeView);
        });


        BorderPane mainPane = new BorderPane();
        mainPane.setTop(inputFolder);
        mainPane.setLeft(treeView);

        Scene scene = new Scene(mainPane);

        stage.setScene(scene);
        stage.setTitle("Catalogs tree");
        stage.setMaximized(true);
        stage.show();
    }

    private void fillTree(String folder, TreeView<String> treeView) {
        TreeFiller filler = new TreeFiller(treeView, folder);

        Platform.runLater(filler);
    }


    public static void main(String[] args) {
        launch();
    }
}