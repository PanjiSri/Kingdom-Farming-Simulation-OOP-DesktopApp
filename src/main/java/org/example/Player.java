package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player {
    private String name;
    private int coin;
    private int card_in_one_turn;
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
        for (int i = 0; i < 5; i++) {
            ArrayList<String> row_lahan = new ArrayList<String>();
            for (int j = 0; j < 4; j++) {
                row_lahan.add(" x ");
            }
            lahan.add(row_lahan);
        }
    }

    public void add_ciot() {
        card_in_one_turn++;
    }

    public int getCard_in_one_turn() {
        return card_in_one_turn;
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

    public void shuffle_to_deck_aktif(String id_kartu) {
        for(int i = 0; i < deck_aktif.size(); i++) {
            if(deck_aktif.get(i).equals("   ")) {
                deck_aktif.add(i, id_kartu);
                System.out.println(id_kartu);
                break;
            }
        }
    }

    public String get_deck (int idx) {
        return deck.get(idx);
    }

    public String get_card_aktif (int idx) {
        return deck_aktif.get(idx);
    }

    public String get_card_ladang (int i, int j) {
        return lahan.get(i).get(j);
    }

    public String getName() {
        return name;
    }

    public int getCoin() {
        return coin;
    }

    public void add_in_lahan(int x, int y, String value) {
        lahan.get(x).set(y, value);
    }

    public void print_lahan() {
        for (int i = 0; i < lahan.size(); i++) {
            for (int j = 0; j < lahan.get(i).size(); j++) {
                System.out.print(lahan.get(i).get(j));
            }
            System.out.println();
        }
    }

    public void print_deck_aktif() {
        for (int i = 0; i < deck_aktif.size(); i++) {
            System.out.println(deck_aktif.get(i));
        }
    }

}
