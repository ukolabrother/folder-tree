package ru.hukola.foldertree;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class TreeFiller implements Runnable {
    private TreeView<String> treeView;
    private TreeItem<String> root;
    private ExecutorService executor;
    private final String folder;

    public TreeFiller(TreeView<String> treeView, String folder) {
        this.treeView = treeView;
        this.folder = folder;
    }

    @Override
    public void run() {
        executor = Executors.newCachedThreadPool();
        Path path = Paths.get(folder);
        root = new TreeItem<>(path.getFileName().toString(), Helper.newFolderImage());
        executor.submit(new CatalogReader(root, path, executor));
        treeView.setRoot(root);
        root.setExpanded(true);
        executor.shutdown();

    }

}
