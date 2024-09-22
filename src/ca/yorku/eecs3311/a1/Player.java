package ca.yorku.eecs3311.a1;

public abstract class Player {
    protected Othello othello;
    protected final char player;

    public Player(char player) {
        this.player = player;
    }

    protected void setOthello(Othello othello) {
        this.othello = othello;
    }

    abstract public Move getMove();
}
