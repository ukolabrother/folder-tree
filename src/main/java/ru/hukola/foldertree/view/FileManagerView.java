package ru.hukola.foldertree.view;

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
import ru.hukola.foldertree.controller.FileManagerController;
import ru.hukola.foldertree.model.FileView;

public class FileManagerView{
	private final FileManagerController controller;
	private TreeView<FileView> tree;
	private TextField inputFolder;
	private TableView<FileView> table;
	private final Stage stage;
	private final String INIT_FOLDER = System.getProperty("user.home");
	
    public FileManagerView(FileManagerController controller, Stage stage) {
		super();
		this.controller = controller;
		this.stage = stage;
	}

    public void show() {
        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
        int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
        
        table = new TableView<FileView>();
        
        TableColumn<FileView, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<FileView, String>("name"));
        table.getColumns().add(nameColumn);

        
        tree = new TreeView<>();
        tree.setPrefWidth(screenWidth / 5);
        tree.setPrefHeight(screenHeight * 90 / 100);
        
        tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue observable, Object oldValue,
            		Object newValue) {
            	TreeItem<FileView> ti = (TreeItem<FileView>) newValue;
            	
            	controller.fillTableFolder(ti.getValue().getPath().toAbsolutePath().toString(), table);
            }

          });

        inputFolder = new TextField();
        inputFolder.setText(INIT_FOLDER);
//        
//        controller.fillTreeFolder(inputFolder.getText(), tree);
        
        inputFolder.setOnAction(e -> {
        	controller.fillTreeFolder(inputFolder.getText(), tree);
        });
        

        

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(inputFolder);
        mainPane.setLeft(tree);
        mainPane.setCenter(table);

        Scene scene = new Scene(mainPane);

        stage.setScene(scene);
        stage.setTitle("Catalogs tree");
        stage.setMaximized(true);
        stage.show();
    }

}