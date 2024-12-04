package othello.viewcontroller;

import javafx.application.Application;
import javafx.stage.Stage;

public class OthelloApplication extends Application {

	@Override
	public void start(Stage stage) {
		new HomeScene(stage);
		stage.show();
	}

	public static void main(String[] args) {
		OthelloApplication view = new OthelloApplication();
		launch(args);
	}
}