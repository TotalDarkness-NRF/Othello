package othello.viewcontroller;

import othello.model.*;
import util.Observable;
import util.Observer;
import javafx.application.Platform;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static util.FileUtil.chooseFile;
import static util.FileUtil.saveOthelloToFile;

public class OthelloScene extends Scene implements Observer {
    final OthelloGame game;
    final GridPane othelloGrid;
    final Text status = new Text();
    final Text player1Count = new Text();
    final Text player2Count = new Text();

    public OthelloScene(Stage stage, OthelloGame game) {
        super(new StackPane());
        this.game = game;
        game.getOthello().attach(this);
        this.othelloGrid = createOthelloBoard();
        createScene(stage);
    }

    private void createScene(Stage stage) {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        root.getChildren().add(createLayout(stage));
        stage.setTitle("Othello");
        stage.setScene(scene);
        getNextMove();
    }

    private VBox createLayout(Stage stage) {
        VBox layout = new VBox(5);
        layout.setAlignment(Pos.CENTER);
        othelloGrid.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(createStatusText(), othelloGrid, createButtons(stage));
        return layout;
    }

    private HBox createButtons(Stage stage) {
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        Button home = new Button("Back");
        home.setOnAction(e -> new PlayerSelectScene(stage));
        Button restart = new Button("Restart");
        restart.setOnAction(e -> new OthelloScene(stage, new OthelloGame(new Othello(), game.getPlayer1(), game.getPlayer2())));
        Button save = new Button("Save");
        save.setOnAction(e -> chooseFile(stage, true).ifPresent(file -> saveOthelloToFile(file, game)));
        Button undo = new Button("Undo");
        Button redo = new Button("Redo");
        buttons.getChildren().addAll(home, save, restart, undo, redo);
        return buttons;
    }

    private HBox createStatusText() {
        HBox statusText = new HBox(10);
        updateText();
        statusText.setAlignment(Pos.CENTER);
        player1Count.setTextAlignment(TextAlignment.LEFT);
        status.setTextAlignment(TextAlignment.CENTER);
        player2Count.setTextAlignment(TextAlignment.RIGHT);
        statusText.getChildren().addAll(player1Count, status, player2Count);
        return statusText;
    }

    private GridPane createOthelloBoard() {
        GridPane grid = new GridPane();
        double squareSize = 50;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle square = new Rectangle(squareSize, squareSize);
                square.setFill(Color.GREEN);
                square.setStroke(Color.BLACK);
                char player = game.getOthello().board.get(row, col);
                if (player == OthelloBoard.EMPTY) {
                    grid.add(square, col, row);
                    final int finalRow = row, finalCol = col;
                    square.setOnMouseClicked(e -> handleOnSquareClick(finalRow, finalCol));
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

    private void getNextMove() {
        Platform.runLater(() -> {
            if (!getMove()) return;
            Move move;
            if (game.getOthello().getWhosTurn() == OthelloBoard.P1) move = game.getPlayer1().getMove();
            else move = game.getPlayer2().getMove();
            game.getOthello().move(move.getRow(), move.getCol());
            if (!(game.getPlayer1() instanceof PlayerHuman || game.getPlayer2() instanceof PlayerHuman)) {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 200);
            }
        });
    }

    private boolean getMove() {
        if (game.getOthello().isGameOver() || game.getOthello().getWhosTurn() == OthelloBoard.EMPTY) return false;
        if (game.getOthello().getWhosTurn() == OthelloBoard.P1 && game.getPlayer1() instanceof PlayerHuman) return false;
        return !(game.getPlayer2() instanceof PlayerHuman);
    }

    private void handleOnSquareClick(int row, int col) {
        game.getOthello().move(row, col);
    }

    private void updateBoard() {
        othelloGrid.getChildren().setAll(createOthelloBoard().getChildren());
        updateText();
        getNextMove();
    }

    private void updateText() {
        StringBuilder builder;
        if (game.getOthello().isGameOver()) {
             builder = new StringBuilder("Game Over: ");
            if (game.getOthello().getWinner() == OthelloBoard.EMPTY) builder.append("Draw");
            else if (game.getOthello().getWinner() == OthelloBoard.P1) builder.append("Black wins!");
            else builder.append("Black wins!");
        } else {
            builder = new StringBuilder("Turn: ");
            if (game.getOthello().getWhosTurn() == OthelloBoard.EMPTY) builder.append("None");
            else if (game.getOthello().getWhosTurn() == OthelloBoard.P1) builder.append("Black");
            else builder.append("White");
        }
        status.setText(builder.toString());
        player1Count.setText("Black: " + game.getOthello().getCount(OthelloBoard.P1));
        player2Count.setText("White: " + game.getOthello().getCount(OthelloBoard.P2));
    }

    @Override
    public void update(Observable o) {
        updateBoard();
    }
}