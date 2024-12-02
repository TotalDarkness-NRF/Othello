package othello.viewcontroller;

import othello.model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
        Scene scene = new Scene(root, 450, 450);
        GridPane grid = new GridPane();

        Button home = new Button("Home");
        Scene oldScene = stage.getScene().getRoot().getScene();
        home.setOnAction(e -> stage.setScene(oldScene));
        grid.add(home, 0, 0);
        grid.add(createOthelloBoard(), 0, 1);
        root.getChildren().add(grid);
        stage.setTitle("Othello");
        stage.setScene(scene);
    }

    private GridPane createOthelloBoard() {
        GridPane grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);
        double squareSize = 50;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(squareSize, squareSize);
                final int finalRow = row, finalCol = col;
                square.setOnMouseClicked(e -> handleOnSquareClick(finalRow, finalCol));
                square.setFill(Color.GREEN);
                square.setStroke(Color.BLACK); // Black border
                Circle circle = new Circle(squareSize / 2, col % 2 == 0 ? Color.BLACK : Color.WHITE);
                circle.setCenterX(squareSize / 2);
                circle.setCenterY(squareSize / 4);
                circle.setScaleX(0.85);
                circle.setScaleY(0.85);

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(square, circle);
                grid.add(stackPane, col, row);
            }
        }
        return grid;
    }

    private void handleOnSquareClick(int row, int col) {
        // TODO handle moving
    }
}