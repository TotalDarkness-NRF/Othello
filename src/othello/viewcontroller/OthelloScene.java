package othello.viewcontroller;

import othello.model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OthelloScene extends Scene {
    final Othello othello;
    final Player player1, player2;

    public OthelloScene(Stage stage, Othello othello, Player player1, Player player2) {
        super(new StackPane());
        this.othello = othello;
        this.player1 = player1;
        this.player2 = player2;
        createScene(stage);
    }

    private void createScene(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 400);

        Button home = new Button("Home");
        Scene oldScene = stage.getScene().getRoot().getScene();
        home.setOnAction(e -> stage.setScene(oldScene));

        root.getChildren().add(home);
        stage.setTitle("Othello");
        stage.setScene(scene);
    }
}