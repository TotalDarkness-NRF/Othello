package othello.viewcontroller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class OthelloApplication extends Application {

	/*
	Assignment 2
GUI, Design Patterns
Due: 	November 30th by 11:59 pm
Hand in:	A zipped copy of your entire project folder and a short video (abount 5 min, narrated screen recording from your laptop) demoing all features with a brief explanation of design patterns applied for each feature. You may upload your video to the cloud and provide a link.
Marking:	Marks will be given for

    Code quality: Individual classes marked according to criteria published for A1.
    Architecture: The extent to which you use Design Patterns to create simple, extendable, clear, concise code.
    Documentation: In addition to normal JavaDoc, please make sure that you describe how classes are used in any Design Patterns. Please make your code so simple, with names so well chosen, that minimal documentation is needed.
    JUnit Testing (not expected for this, so no marks here)

Features:

    MVC architecture
    Create GUI for Human VS Human - board of buttons with images
    Design pattern: strategy for which player
    Allow choice of opponents: GUI
    Visitor for OthelloBoard+rewrite of lots of Othello and OthelloBoard
    Undo/redo move: Command design pattern + game history
    Saving game to file
    Load game from file
    Restart game button
    Undo move button
    GUI to allow choice of different player types
	 */

	@Override
	public void start(Stage stage) throws Exception {
		/*
		// Create and hook up the Model, View and the controller

		// MODEL
		Othello othello = new Othello();

		// CONTROLLER
		// CONTROLLER->MODEL hookup
		// https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm#CEGJAAFD
		// https://docs.oracle.com/javase/8/javafx/api/javafx/event/Event.html
		//CButtonPressEventHandler cpresshandler=new CButtonPressEventHandler(mcounter);
		Button playButton = new Button("Play");

		// VIEW
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup

		//GridPane grid = new GridPane();
		HBox root = new HBox();
		root.setPadding(new Insets(5));
		root.getChildren().addAll(playButton);

		// SCENE
		//Scene scene = new Scene(grid);
		Scene scene = new Scene(root);
		stage.setTitle("Othello");
		stage.setScene(scene);

		// LAUNCH THE GUI
		stage.show();
		 */
		// Create the first scene with a button
		//StackPane root = new StackPane();
		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 400, 400);
		Button play = new Button("Play Othello");
		play.setOnAction(e -> new PlayerSelectScene(stage));
		Button load = new Button("Load Game");
		load.setOnAction(e -> chooseFile(stage));
		Button exit = new Button("Exit");
		exit.setOnAction(e -> stage.close());
		root.getChildren().addAll(play, load, exit);
		stage.setTitle("Home Othello");
		stage.setScene(scene);
		stage.show();
	}

	private void chooseFile(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Othello Board");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Othello Files", "*.othello"));
		File file = fileChooser.showSaveDialog(stage);
	}


	public static void main(String[] args) {
		OthelloApplication view = new OthelloApplication();
		launch(args);
	}
}
