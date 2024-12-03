package othello.viewcontroller;

import util.FileUtil;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static util.FileUtil.chooseFile;


public class HomeScene extends Scene {

    public HomeScene(Stage stage) {
        super(new StackPane());
        createScene(stage);
    }

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

    private void loadOthelloGame(Stage stage) {
        chooseFile(stage, false)
                .flatMap(FileUtil::loadOthelloFile)
                .ifPresent(game -> new OthelloScene(stage, game));
    }
}