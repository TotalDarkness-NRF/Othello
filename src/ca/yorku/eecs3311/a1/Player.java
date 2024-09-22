package ca.yorku.eecs3311.a1;

public abstract class Player {
    protected Othello othello;
    protected char player;

    protected void setOthello(Othello othello) {
        this.othello = othello;
    }

    protected void setPlayer(char player) {
        this.player = player;
    }

    abstract public Move getMove();
}
