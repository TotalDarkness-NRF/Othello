package othello.viewcontroller;
import othello.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
		// Create and hook up the Model, View and the controller

		// MODEL
		Othello othello = new Othello();

		// CONTROLLER
		// CONTROLLER->MODEL hookup

		// VIEW
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup

		GridPane grid = new GridPane();

		// SCENE
		Scene scene = new Scene(grid);
		stage.setTitle("Othello");
		stage.setScene(scene);

		// LAUNCH THE GUI
		stage.show();
	}

	public static void main(String[] args) {
		OthelloApplication view = new OthelloApplication();
		launch(args);
	}
}
