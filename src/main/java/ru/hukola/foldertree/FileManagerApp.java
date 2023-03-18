package ru.hukola.foldertree;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.hukola.foldertree.controller.FileManagerController;
import ru.hukola.foldertree.model.FileManagerModel;
import ru.hukola.foldertree.view.FileManagerView;

public class FileManagerApp extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FileManagerModel model = new FileManagerModel();
		FileManagerController controller = new FileManagerController(model);
		FileManagerView view = new FileManagerView(controller, primaryStage);
		view.show();
	}

}
