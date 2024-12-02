package othello.viewcontroller;

import othello.model.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

        VBox layout = createLayout();
        Button play = new Button("Play");
        play.setOnAction(_ -> switchToPlayScene(stage));

        Button home = new Button("Back");
        home.setOnAction(_ -> new HomeScene(stage));
        layout.getChildren().addAll(play, home);

        root.getChildren().add(layout);
        stage.setTitle("Select Player");
        stage.setScene(scene);
    }

    private VBox createLayout() {
        // TODO use strategy design pattern
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        HBox playerButtons = new HBox(20);
        playerButtons.setAlignment(Pos.CENTER);
        VBox player1Buttons = new VBox(10);
        player1Buttons.setAlignment(Pos.CENTER_LEFT);
        VBox player2Buttons = new VBox(10);
        player2Buttons.setAlignment(Pos.CENTER_RIGHT);

        Button playerHuman1 = new Button("Player Human");
        playerHuman1.setOnAction(_ -> setPlayer1(new PlayerHuman(othello, OthelloBoard.P1)));
        Button playerGreedy1 = new Button("Player Greedy");
        playerGreedy1.setOnAction(_ -> setPlayer1(new PlayerGreedy(othello, OthelloBoard.P1)));
        Button playerRandom1 = new Button("Player Random");
        playerRandom1.setOnAction(_ -> setPlayer1(new PlayerRandom(othello, OthelloBoard.P1)));
        player1Buttons.getChildren().addAll(playerHuman1, playerGreedy1, playerRandom1, player1Text);

        Button playerHuman2 = new Button("Player Human");
        playerHuman2.setOnAction(_ -> setPlayer2(new PlayerHuman(othello, OthelloBoard.P2)));
        Button playerGreedy2 = new Button("Player Greedy");
        playerGreedy2.setOnAction(_ -> setPlayer2(new  PlayerGreedy(othello, OthelloBoard.P2)));
        Button playerRandom2 = new Button("Player Random");
        playerRandom2.setOnAction(_ -> setPlayer2(new PlayerRandom(othello, OthelloBoard.P2)));
        player2Buttons.getChildren().addAll(playerHuman2, playerGreedy2, playerRandom2, player2Text);

        playerButtons.getChildren().addAll(player1Buttons, player2Buttons);

        layout.getChildren().addAll(new Text("Select Player!"), playerButtons);
        return layout;
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