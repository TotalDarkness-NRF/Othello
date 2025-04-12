package net.totaldarkness.othello.viewcontroller;

import net.totaldarkness.util.FileUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static net.totaldarkness.util.FileUtil.chooseFile;

/**
 * A HomeScene that sets the HomeScene of the OthelloApplication.
 * It contains a Play Othello button to open a PlayerSelectScene,
 * a LoadGame button to load an othello game and an exit button to
 * exit the OthelloApplication.
 */
public class HomeScene extends Scene {

    public HomeScene(Stage stage) {
        super(new StackPane());
        createScene(stage);
    }

    /**
     * Creates the scene and sets the scene to the provided stage.
     * @param stage The stage to control
     */
    private void createScene(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 500);
        Button play = new Button("Play Othello");
        play.setOnAction(e -> new PlayerSelectScene(stage));
        Button load = new Button("Load Game");
        load.setOnAction(e -> loadOthelloGame(stage));
        Button exit = new Button("Exit");
        exit.setOnAction(e -> stage.close());
        root.getChildren().addAll(play, load, exit);
        stage.setTitle("Home Othello");
        stage.setScene(scene);
    }

    /**
     * Load the Othello game and open an OthelloScene to
     * show the othello game scene.
     * @param stage The stage to control
     */
    private void loadOthelloGame(Stage stage) {
        chooseFile(stage, false)
                .flatMap(FileUtil::loadOthelloFile)
                .ifPresent(game -> new OthelloScene(stage, game));
    }
}