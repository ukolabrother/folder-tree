package ru.hukola.foldertree.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javafx.scene.control.TableView;

public class TableCatalogReader implements Runnable {
	private final Path catalog;
	private final TableView<FileView> table;

	public TableCatalogReader(TableView<FileView> table, Path catalog) {
		super();
		this.catalog = catalog;
		this.table = table;
	}

	@Override
	public void run() {
		table.getItems().clear();
		try {
			List<Path> paths = Files.list(catalog).toList();
			for (Path file : paths) {
				FileView fw = new FileView(file.getFileName().toString(), file.getFileName());
				table.getItems().add(fw);
			}
		} catch (IOException e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
		
	}

}
