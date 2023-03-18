package ru.hukola.foldertree.controller;

import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import ru.hukola.foldertree.model.FileManagerModel;
import ru.hukola.foldertree.model.FileView;

public class FileManagerController {
	
	private final FileManagerModel model;


	public FileManagerController(FileManagerModel model) {
		super();
		this.model = model;
	}



	public void fillTreeFolder(String folder, TreeView<FileView> treeView) {
		model.fillTreeFolder(folder, treeView);
	}

	public void fillTableFolder(String absotulePath, TableView<FileView> table) {
		model.fillTableFolder(absotulePath, table);
		
	}

}
