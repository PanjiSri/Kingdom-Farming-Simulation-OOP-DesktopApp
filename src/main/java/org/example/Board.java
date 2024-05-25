package org.example;

public class Board {
    private  Player p1;
    private  Player p2;
    int turn;
    int totalturn;
    private Toko toko;

    public Board(Player _p1, Player _p2, Toko toko) {
        p1 = _p1;
        p2 = _p2;
        turn = 1;
        totalturn = 1;
        this.toko = toko;
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
        totalturn++;
    }

    public int getTotalturn() {
        return totalturn;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public void set_totalturn(int totalturn) {
        this.totalturn = totalturn;
    }

    public int getTurn() {
        return turn;
    }

    public Toko getToko() {
        return toko;
    }
}
