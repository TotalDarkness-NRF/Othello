package othello.model;

import util.OthelloMoveCommandManager;

import java.io.Serializable;

public final class OthelloGame implements Serializable {
    private final Player player1, player2;
    final OthelloMoveCommandManager commandManager;

    public OthelloGame(Othello othello, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.commandManager = new OthelloMoveCommandManager(othello);
    }

    public Othello getOthello() {
        return commandManager.getOthello();
    }

    public void restartGame() {
        Othello newOthello = new Othello();
        setOthello(newOthello);
        commandManager.setOthello(newOthello);
    }

    public void setOthello(Othello othello) {
        player1.othello = othello;
        player2.othello = othello;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public OthelloMoveCommandManager getCommandManager() {
        return commandManager;
    }
}