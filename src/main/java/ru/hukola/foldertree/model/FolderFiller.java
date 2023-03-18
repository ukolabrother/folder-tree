package ru.hukola.foldertree.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.TableView;

public class FolderFiller implements Runnable{
	private final TableView<FileView> table;
	private final String absolutePath;
	private ExecutorService executor;
	
	public FolderFiller(TableView<FileView> table, String absolutePath) {
		super();
		this.table = table;
		this.absolutePath = absolutePath;
	}

	@Override
	public void run() {
		executor = Executors.newSingleThreadExecutor();
        Path path = Paths.get(absolutePath);
        
        executor.submit(new TableCatalogReader(table, path));      
        executor.shutdown();
        try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
			System.out.println(table.getItems().size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
