package othello.viewcontroller;

import javafx.application.Application;
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

    Done: MVC architecture
    Done: Create GUI for Human VS Human - board of buttons with images
    Done: Allow choice of opponents: GUI
    Done: Saving game to file
    Done: Load game from file
    Done: Restart game button
    Done: GUI to allow choice of different player types
    Done: Design pattern: strategy for which player
    Visitor for OthelloBoard+rewrite of lots of Othello and OthelloBoard
    Undo/redo move: Command design pattern + game history
    Undo move button
	 */

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
