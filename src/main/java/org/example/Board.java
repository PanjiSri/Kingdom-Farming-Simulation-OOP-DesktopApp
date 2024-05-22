package org.example;

public class Board {
    private  Player p1;
    private  Player p2;
    int turn;
    int totalturn;

    public Board(Player _p1, Player _p2) {
        p1 = _p1;
        p2 = _p2;
        turn = 1;
        totalturn = 0;
    }

    public Player getPlayernow() {
        if (turn == 1) {
            return p1;
        }
        return p2;
    }

    public Player getEnemyNow() {
        if (turn == 1) {
            return p2;
        }
        return p1;
    }

    public void add_turn() {
        if (turn == 1) {
            turn = 2;
        }
        else {
            turn = 1;
            totalturn += 1;
        }
    }

    public int getTurn() {
        return turn;
    }
}
