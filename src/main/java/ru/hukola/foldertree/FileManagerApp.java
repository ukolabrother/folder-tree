package ru.hukola.foldertree;

import java.nio.file.Path;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FileManagerApp extends Application {
    @Override
    public void start(Stage stage) {
        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
        int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();

        TreeView<FileView> treeView = new TreeView<>();
        treeView.setPrefWidth(screenWidth / 5);
        treeView.setPrefHeight(screenHeight * 90 / 100);
        
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue observable, Object oldValue,
            		Object newValue) {
            	TreeItem<FileView> ti = (TreeItem<FileView>) newValue;
            	
            	System.out.println(ti.getValue().getPath().toAbsolutePath());
      

//                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
//                System.out.println("Selected Text : " + selectedItem.getValue());
                // do what ever you want 
            }

          });

        TextField inputFolder = new TextField();
        inputFolder.setOnAction(e -> {
            fillTree(inputFolder.getText(), treeView);
        });
        
        ObservableList<FileView> files = FXCollections.observableArrayList(
        		new FileView("folder1", Path.of("/")),
        		new FileView("file2", Path.of("/")));
        
        TableView<FileView> table = new TableView<FileView>(files);
        
        TableColumn<FileView, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<FileView, String>("name"));
        
        table.getColumns().add(nameColumn);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(inputFolder);
        mainPane.setLeft(treeView);
        mainPane.setCenter(table);

        Scene scene = new Scene(mainPane);

        stage.setScene(scene);
        stage.setTitle("Catalogs tree");
        stage.setMaximized(true);
        stage.show();
    }

    private void fillTree(String folder, TreeView<FileView> treeView) {
        TreeFiller filler = new TreeFiller(treeView, folder);

        Platform.runLater(filler);
    }


    public static void main(String[] args) {
        launch();
    }
}