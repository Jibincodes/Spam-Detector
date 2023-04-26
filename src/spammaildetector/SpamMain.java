package spammaildetector;

import javafx.application.Application;
import javafx.stage.Stage;

public class SpamMain extends Application {
	private SpamModel model;
	private SpamView view;
	private SpamController controller;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		model = new SpamModel();
		view = new SpamView(primaryStage, model, controller);
		controller = new SpamController(model, view);
		view.setController(controller);
		view.start();

	}

	public static void main(String[] args) {
		launch();

	}

}
