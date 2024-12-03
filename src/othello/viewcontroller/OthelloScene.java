package othello.viewcontroller;

import othello.model.*;
import util.*;
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
        this.othelloGrid = createOthelloBoard();
        createScene(stage);
    }

    private void createNewGame() {
        game.restartGame();
        updateBoard();
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
        restart.setOnAction(e -> createNewGame());
        Button save = new Button("Save");
        save.setOnAction(e -> chooseFile(stage, true).ifPresent(file -> saveOthelloToFile(file, game)));
        Button undo = new Button("Undo");
        undo.setOnAction(e -> undo());
        Button redo = new Button("Redo");
        redo.setOnAction(e -> redo());
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

    private Rectangle createSquare(double size) {
        Rectangle square = new Rectangle(size, size);
        square.setFill(Color.GREEN);
        square.setStroke(Color.BLACK);
        return square;
    }

    private Circle createCircle(double size, Color color) {
        Circle circle = new Circle(size / 2, color);
        circle.setCenterX(size / 2);
        circle.setCenterY(size / 4);
        circle.setScaleX(0.85);
        circle.setScaleY(0.85);
        return circle;
    }

    private StackPane createPiece(double size, char player) {
        StackPane stackPane = new StackPane();
        Rectangle square = createSquare(size);
        Circle circle = createCircle(size, player == OthelloBoard.P1 ? Color.BLACK : Color.WHITE);
        stackPane.getChildren().addAll(square, circle);
        return stackPane;
    }

    private void createOthelloGrid(GridPane grid, OthelloBoardPiecesVisitor visitor, double size) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (visitor.getPieces().containsKey(new Move(row, col))) continue;
                Rectangle square = createSquare(size);
                final int finalRow = row, finalCol = col;
                square.setOnMouseClicked(e -> handleOnSquareClick(finalRow, finalCol));
                grid.add(square, finalCol, finalRow);
            }
        }
    }

    private GridPane createOthelloBoard() {
        final double size = 50;
        GridPane grid = new GridPane();
        OthelloBoardPiecesVisitor piecesVisitor = new OthelloBoardPiecesVisitor();
        getOthello().board.accept(piecesVisitor);
        createOthelloGrid(grid, piecesVisitor, size);
        piecesVisitor.getPieces().forEach((move, player) -> grid.add(createPiece(size, player), move.getCol(), move.getRow()));
        return grid;
    }

    private void getNextMove() {
        Platform.runLater(() -> {
            if (!hasMove()) return;
            Move move;
            if (getOthello().getWhosTurn() == OthelloBoard.P1) move = game.getPlayer1().getMove();
            else move = game.getPlayer2().getMove();
            move(move);
            if (!(game.getPlayer1() instanceof PlayerHuman || game.getPlayer2() instanceof PlayerHuman)) {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 200);
            }
        });
    }

    private boolean hasMove() {
        if (getOthello().isGameOver() || getOthello().getWhosTurn() == OthelloBoard.EMPTY) return false;
        if (getOthello().getWhosTurn() == OthelloBoard.P1 && game.getPlayer1() instanceof PlayerHuman) return false;
        return !(game.getPlayer2() instanceof PlayerHuman);
    }

    private void move(Move move) {
        if (!getOthello().copy().move(move.getRow(), move.getCol())) return;
        getOthello().attach(this);
        game.getCommandManager().executeCommand(new OthelloMoveCommand(move, getOthello()));
        getOthello().detach(this);
    }

    private void undo() {
        game.getCommandManager().undo();
        updateBoard();
    }

    private void redo() {
        game.getCommandManager().redo();
        updateBoard();
    }

    private void handleOnSquareClick(int row, int col) {
        move(new Move(row, col));
    }

    private void updateBoard() {
        game.setOthello(getOthello());
        othelloGrid.getChildren().setAll(createOthelloBoard().getChildren());
        updateText();
        getNextMove();
    }

    private Othello getOthello() {
        return game.getOthello();
    }

    private void updateText() {
        StringBuilder builder;
        if (getOthello().isGameOver()) {
             builder = new StringBuilder("Game Over: ");
            if (getOthello().getWinner() == OthelloBoard.EMPTY) builder.append("Draw");
            else if (getOthello().getWinner() == OthelloBoard.P1) builder.append("Black wins!");
            else builder.append("White wins!");
        } else {
            builder = new StringBuilder("Turn: ");
            if (getOthello().getWhosTurn() == OthelloBoard.EMPTY) builder.append("None");
            else if (getOthello().getWhosTurn() == OthelloBoard.P1) builder.append("Black");
            else builder.append("White");
        }
        status.setText(builder.toString());
        OthelloBoardCountVisitor countVisitor = new OthelloBoardCountVisitor();
        countVisitor.visit(getOthello().board);
        player1Count.setText("Black: " + countVisitor.getPlayer1Count());
        player2Count.setText("White: " + countVisitor.getPlayer2Count());
    }

    @Override
    public void update(Observable o) {
        updateBoard();
    }
}