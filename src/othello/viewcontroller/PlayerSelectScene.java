package othello.viewcontroller;

import othello.model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerSelectScene extends Scene {
    Player player1, player2;

    public PlayerSelectScene(Stage stage) {
        super(new StackPane());
        createScene(stage);
    }

    private void createScene(Stage stage) {
        // Create the second scene
        Button btn2 = new Button("Home");
        StackPane root = new StackPane();
        root.getChildren().add(createButtonGrid());
        Scene scene2 = new Scene(root, 300, 200);
        Scene oldScene = stage.getScene().getRoot().getScene();

        // Action for the second button to go back to the first scene
        btn2.setOnAction(e -> stage.setScene(oldScene));

        // Switch to the new scene
        stage.setScene(scene2);
    }

    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        Othello othello = new Othello();
        Button playerHuman1 = new Button("Player Human");
        playerHuman1.setOnAction(e -> player1 = new PlayerHuman(othello, OthelloBoard.P1));
        Button playerGreedy1 = new Button("Player Greedy");
        playerGreedy1.setOnAction(e -> player1 = new PlayerGreedy(othello, OthelloBoard.P1));
        Button playerRandom1 = new Button("Player Random");
        playerRandom1.setOnAction(e -> player1 = new PlayerRandom(othello, OthelloBoard.P1));
        Button playerHuman2 = new Button("Player Human");
        playerHuman2.setOnAction(e -> player2 = new PlayerHuman(othello, OthelloBoard.P2));
        Button playerGreedy2 = new Button("Player Greedy");
        playerHuman1.setOnAction(e -> player2 = new PlayerGreedy(othello, OthelloBoard.P2));
        Button playerRandom2 = new Button("Player Random");
        playerHuman1.setOnAction(e -> player2 = new PlayerRandom(othello, OthelloBoard.P2));

        grid.add(playerHuman1, 0, 1);
        grid.add(playerGreedy1, 0, 2);
        grid.add(playerRandom1, 0, 3);
        grid.add(playerHuman2, 2, 1);
        grid.add(playerGreedy2, 2, 2);
        grid.add(playerRandom2, 2, 3);
        grid.add(new Text("Select Player!"), 1, 0);

        // TODO show which player selected!
        // TODO show button to start player once both players selected
        return grid;
    }
}