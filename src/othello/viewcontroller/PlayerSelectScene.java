package othello.viewcontroller;

import othello.model.*;
import util.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A PlayerSelectScene that sets the PlayerSelectScene for the OthelloApplication.
 * It contains buttons to select the player strategies for both player1 and player2. The player
 * strategies include a PlayerHuman, a PlayerGreedy, and a PlayerRandom.
 *
 */
public class PlayerSelectScene extends Scene {
    Othello othello = new Othello();
    PlayerContext player1 = new PlayerContext(new PlayerHumanStrategy(othello, OthelloBoard.P1));
    PlayerContext player2 = new PlayerContext(new PlayerHumanStrategy(othello, OthelloBoard.P2));
    final Text player1Text = new Text(String.format("Player 1: %s", player1.getPlayer()));
    final Text player2Text = new Text(String.format("Player 2: %s", player2.getPlayer()));

    public PlayerSelectScene(Stage stage) {
        super(new StackPane());
        createScene(stage);
    }

    /**
     * Creates the scene and sets the scene to the provided stage.
     * @param stage The stage to control
     */
    private void createScene(Stage stage) {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());

        VBox layout = createLayout();
        Button play = new Button("Play");
        play.setOnAction(e -> switchToPlayScene(stage));

        Button home = new Button("Back");
        home.setOnAction(e -> new HomeScene(stage));
        layout.getChildren().addAll(play, home);

        root.getChildren().add(layout);
        stage.setTitle("Select Player");
        stage.setScene(scene);
    }

    /**
     * Creates the scenes layout and position everything correctly
     * and in order.
     * @return A Vertical Box with the layout aligned correctly and in order.
     */
    private VBox createLayout() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        HBox playerButtons = new HBox(20);
        playerButtons.setAlignment(Pos.CENTER);
        VBox player1Buttons = new VBox(10);
        player1Buttons.setAlignment(Pos.CENTER_LEFT);
        VBox player2Buttons = new VBox(10);
        player2Buttons.setAlignment(Pos.CENTER_RIGHT);

        Button playerHuman1 = new Button("Player Human");
        playerHuman1.setOnAction(e -> setPlayer1(new PlayerHumanStrategy(othello, OthelloBoard.P1)));
        Button playerGreedy1 = new Button("Player Greedy");
        playerGreedy1.setOnAction(e -> setPlayer1(new PlayerGreedyStrategy(othello, OthelloBoard.P1)));
        Button playerRandom1 = new Button("Player Random");
        playerRandom1.setOnAction(e -> setPlayer1(new PlayerRandomStrategy(othello, OthelloBoard.P1)));
        player1Buttons.getChildren().addAll(playerHuman1, playerGreedy1, playerRandom1, player1Text);

        Button playerHuman2 = new Button("Player Human");
        playerHuman2.setOnAction(e -> setPlayer2(new PlayerHumanStrategy(othello, OthelloBoard.P2)));
        Button playerGreedy2 = new Button("Player Greedy");
        playerGreedy2.setOnAction(e -> setPlayer2(new PlayerGreedyStrategy(othello, OthelloBoard.P2)));
        Button playerRandom2 = new Button("Player Random");
        playerRandom2.setOnAction(e -> setPlayer2(new PlayerRandomStrategy(othello, OthelloBoard.P2)));
        player2Buttons.getChildren().addAll(playerHuman2, playerGreedy2, playerRandom2, player2Text);

        playerButtons.getChildren().addAll(player1Buttons, player2Buttons);

        layout.getChildren().addAll(new Text("Select Player!"), playerButtons);
        return layout;
    }

    /**
     * Switches to the OthelloScene to start playing the game if both players
     * have selected a player.
     * @param stage The stage to control.
     */
    private void switchToPlayScene(Stage stage) {
        if (player1 == null || player2 == null) return;
        new OthelloScene(stage, new OthelloGame(othello, player1.getPlayer(), player2.getPlayer()));
    }

    public void setPlayer1(PlayerStrategy player) {
        player1.setStrategy(player);
        player1Text.setText(String.format("Player 1: %s", player1.getPlayer()));
    }

    public void setPlayer2(PlayerStrategy player) {
        player2.setStrategy(player);
        player2Text.setText(String.format("Player 2: %s", player2.getPlayer()));
    }
}