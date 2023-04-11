package spammaildetector;

import javafx.stage.Stage;

public class SpamView {
	protected Stage stage;
	private SpamModel model;

	public SpamView(Stage primaryStage, SpamModel model) {
		this.stage = primaryStage;
		this.model = model;
	}

	public void start() {
		stage.show();
		
	}

}
