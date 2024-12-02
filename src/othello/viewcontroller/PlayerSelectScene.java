package othello.viewcontroller;

import othello.model.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerSelectScene extends Scene {
    Othello othello = new Othello();
    Player player1, player2;
    final Text player1Text = new Text("Not Selected");
    final Text player2Text = new Text("Not Selected");

    public PlayerSelectScene(Stage stage) {
        super(new StackPane());
        createScene(stage);
    }

    private void createScene(Stage stage) {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 400, 400);

        GridPane grid = createButtonGrid();
        Button play = new Button("Play");
        play.setOnAction(e -> switchToPlayScene(stage));
        grid.add(play, 1, 5);

        Button home = new Button("Home");
        Scene oldScene = stage.getScene().getRoot().getScene();
        home.setOnAction(e -> stage.setScene(oldScene));
        grid.add(home, 1 , 6);

        root.getChildren().add(grid);
        stage.setTitle("Select Player");
        stage.setScene(scene);
    }

    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        // TODO use strategy design pattern
        Button playerHuman1 = new Button("Player Human");
        playerHuman1.setOnAction(e -> setPlayer1(new PlayerHuman(othello, OthelloBoard.P1)));
        Button playerGreedy1 = new Button("Player Greedy");
        playerGreedy1.setOnAction(e -> setPlayer1(new PlayerGreedy(othello, OthelloBoard.P1)));
        Button playerRandom1 = new Button("Player Random");
        playerRandom1.setOnAction(e -> setPlayer1(new PlayerRandom(othello, OthelloBoard.P1)));
        Button playerHuman2 = new Button("Player Human");
        playerHuman2.setOnAction(e -> setPlayer2(new PlayerHuman(othello, OthelloBoard.P2)));
        Button playerGreedy2 = new Button("Player Greedy");
        playerGreedy2.setOnAction(e -> setPlayer2(new  PlayerGreedy(othello, OthelloBoard.P2)));
        Button playerRandom2 = new Button("Player Random");
        playerRandom2.setOnAction(e -> setPlayer2(new PlayerRandom(othello, OthelloBoard.P2)));

        grid.add(playerHuman1, 0, 1);
        grid.add(playerGreedy1, 0, 2);
        grid.add(playerRandom1, 0, 3);
        grid.add(playerHuman2, 2, 1);
        grid.add(playerGreedy2, 2, 2);
        grid.add(playerRandom2, 2, 3);
        grid.add(new Text("Select Player!"), 1, 0);
        grid.add(player1Text, 0, 4);
        grid.add(player2Text, 2, 4);
        return grid;
    }

    private void switchToPlayScene(Stage stage) {
        if (player1 == null || player2 == null) return;
        new OthelloScene(stage, othello, player1, player2);
    }

    public void setPlayer1(Player player) {
        this.player1 = player;
        player1Text.setText(String.format("Player 1: %s", player1));
    }

    public void setPlayer2(Player player) {
        this.player2 = player;
        player2Text.setText(String.format("Player 2: %s", player2));
    }
}