package othello.viewcontroller;

import othello.model.OthelloGame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

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
        load.setOnAction(e -> loadOthelloGame(stage));
        Button exit = new Button("Exit");
        exit.setOnAction(e -> stage.close());
        root.getChildren().addAll(play, load, exit);
        stage.setTitle("Home Othello");
        stage.setScene(scene);
    }

    private void loadOthelloGame(Stage stage) {
        chooseFile(stage, false)
                .flatMap(this::loadOthelloFile)
                .ifPresent(game -> new OthelloScene(stage, game.getOthello(), game.getPlayer1(), game.getPlayer2()));
    }

    private Optional<OthelloGame> loadOthelloFile(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            OthelloGame othelloGame = (OthelloGame) in.readObject();
            return Optional.ofNullable(othelloGame);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}