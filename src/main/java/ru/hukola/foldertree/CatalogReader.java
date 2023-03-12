package ru.hukola.foldertree;

import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CatalogReader implements Runnable{
    private final TreeItem<String> parent;
    private final Path catalog;
    private final ExecutorService executor;

    public CatalogReader(TreeItem<String> parent, Path catalog, ExecutorService executor) {
        this.parent = parent;
        this.catalog = catalog;
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            List<Path> paths = Files.list(catalog).toList();
            for (Path file : paths) {
                TreeItem<String> item = null;
                if (Files.isHidden(file)) {
                    continue;
                }
                if (Files.isDirectory(file)) {
                    item = new TreeItem<>(file.getFileName().toString(), Helper.newFolderImage());
                    new CatalogReader(item, file, executor).run();
//                    executor.execute();
                } else {
                    item = new TreeItem<>(file.getFileName().toString());
                }
                parent.getChildren().add(item);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
