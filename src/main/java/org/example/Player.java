package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player {
    private String name;
    private int coin;
    ArrayList<String> deck;
    ArrayList<String> deck_aktif;
    ArrayList<ArrayList<String>> lahan;

    public Player(String _name, int _coin) {
        name = _name;
        coin = _coin;
        deck = new ArrayList<String>();
        deck_aktif = new ArrayList<String>();
        lahan = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 40; i++) {
            deck.add(Integer.toString(i));
        }
        for (int i = 0; i < 6; i++) {
            deck_aktif.add("   ");
        }
        for (int i = 0; i < 4; i++) {
            ArrayList<String> row_lahan = new ArrayList<String>();
            for (int j = 0; j < 5; j++) {
                row_lahan.add("   ");
            }
            lahan.add(row_lahan);
        }
    }

    private void kartuToLahan (String _kartu) {
        int card_on_deck = 0;
        for(int i = 0; i < deck_aktif.size(); i++) {
            if(deck_aktif.get(i).equals("   ")) {
                deck_aktif.add(i, _kartu);
            }
            else {
                card_on_deck += 1;
            }
        }
        if (card_on_deck == 6) {
            System.out.println("Kartu penuh");
        }
    }

    public void  cek_ladang_lawan() {

    }

    public void  beli() {

    }

    public void use_kartu() {

    }

    public String get_kartu(int idx) {
        return deck.get(idx);
    }

    public void take_card() {
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public String getName() {
        return name;
    }

    public int getCoin() {
        return coin;
    }


}
