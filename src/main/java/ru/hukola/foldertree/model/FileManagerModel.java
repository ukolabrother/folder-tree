package ru.hukola.foldertree.model;

import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;

public class FileManagerModel {

	public void fillTreeFolder(String folder, TreeView<FileView> tree) {
		TreeFiller filler = new TreeFiller(tree, folder);
        Platform.runLater(filler);		
	}
	
	public void fillTableFolder(String absolutePath, TableView<FileView> table) {
        FolderFiller filler = new FolderFiller(table, absolutePath);
        Platform.runLater(filler);
	}

}
