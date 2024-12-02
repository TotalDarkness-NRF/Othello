package othello.viewcontroller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

import static util.Util.chooseFile;

public class HomeScene extends Scene {

    public HomeScene(Stage stage) {
        super(new StackPane());
        createScene(stage);
    }

    private void createScene(Stage stage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 400, 400);
        Button play = new Button("Play Othello");
        play.setOnAction(e -> new PlayerSelectScene(stage));
        Button load = new Button("Load Game");
        load.setOnAction(e -> chooseFile(stage).ifPresent(this::loadOthelloFile));
        Button exit = new Button("Exit");
        exit.setOnAction(e -> stage.close());
        root.getChildren().addAll(play, load, exit);
        stage.setTitle("Home Othello");
        stage.setScene(scene);
    }

    private void loadOthelloFile(File file) {

    }
}