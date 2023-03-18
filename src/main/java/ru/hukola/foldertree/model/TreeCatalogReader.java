package ru.hukola.foldertree.model;

import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class TreeCatalogReader implements Runnable{
    private final TreeItem<FileView> parent;
    private final Path catalog;
    private final ExecutorService executor;

  

    public TreeCatalogReader(TreeItem<FileView> parent, Path catalog, ExecutorService executor) {
        this.parent = parent;
        this.catalog = catalog;
        this.executor = executor;

    }

    @Override
    public void run() {
        try {
            List<Path> paths = Files.list(catalog).toList();
            for (Path file : paths) {
                TreeItem<FileView> item = null;
                if (Files.isHidden(file)) {
                    continue;
                }
                if (Files.isDirectory(file)) {

                	FileView fw = new FileView(file.getFileName().toString(), file);
                    item = new TreeItem<>(fw, Helper.newFolderImage());
                    new TreeCatalogReader(item, file, executor).run();
//                } else {
//                	FileView fw = new FileView(file.getFileName().toString(), file.getFileName());
//                    item = new TreeItem<>(fw);
                }
                parent.getChildren().add(item);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
