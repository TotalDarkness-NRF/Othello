package othello.viewcontroller;

import othello.model.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static util.Util.chooseFile;

public class OthelloScene extends Scene {
    final Othello othello;
    final Player player1, player2;
    final GridPane othelloGrid;

    public OthelloScene(Stage stage, Othello othello, Player player1, Player player2) {
        super(new StackPane());
        this.othello = othello;
        this.player1 = player1;
        this.player2 = player2;
        this.othelloGrid = createOthelloBoard();
        createScene(stage);
    }

    private void createScene(Stage stage) {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        othelloGrid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 450, 450);
        VBox layout = new VBox(5);
        layout.setAlignment(Pos.CENTER);
        Button home = new Button("Back");
        home.setOnAction(_ -> new PlayerSelectScene(stage));
        Button restart = new Button("Restart");
        restart.setOnAction(_ -> new OthelloScene(stage, new Othello(), player1, player2));
        Button save = new Button("Save");
        save.setOnAction(_ -> chooseFile(stage, true).ifPresent(this::saveOthelloToFile));
        Button undo = new Button("Undo");
        Button redo = new Button("Redo");
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(home, save, restart, undo, redo);
        layout.getChildren().addAll(othelloGrid, buttons);
        root.getChildren().add(layout);
        stage.setTitle("Othello");
        stage.setScene(scene);
    }

    private GridPane createOthelloBoard() {
        GridPane grid = new GridPane();
        double squareSize = 50;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(squareSize, squareSize);
                square.setFill(Color.GREEN);
                square.setStroke(Color.BLACK);
                char player = othello.board.get(row, col);
                if (player == OthelloBoard.EMPTY) {
                    grid.add(square, col, row);
                    final int finalRow = row, finalCol = col;
                    square.setOnMouseClicked(_ -> handleOnSquareClick(finalRow, finalCol));
                    continue;
                }
                Color color = player == OthelloBoard.P1 ? Color.BLACK : Color.WHITE;
                Circle circle = new Circle(squareSize / 2, color);

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
        if (othello.move(row, col)) updateBoard();
    }

    private void updateBoard() {
        othelloGrid.getChildren().setAll(createOthelloBoard().getChildren());
    }

    private void saveOthelloToFile(File file) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new OthelloGame(othello, player1, player2));
        } catch (IOException e) {
            System.out.println("Othello game failed to save to file!");
        }
    }
}