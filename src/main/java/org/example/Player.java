package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.example.card.CardGUI;
import org.example.card.Card;
import org.example.card.Hewan.Beruang;

public class Player {
    private String name;
    private int coin;
    private int card_in_one_turn;
    ArrayList<Card> deck;
    ArrayList<Card> deck_aktif;
    ArrayList<ArrayList<Card>> lahan;

    public Player(String _name, int _coin) {
        name = _name;
        coin = _coin;
        deck = new ArrayList<Card>();
        deck_aktif = new ArrayList<Card>();
        lahan = new ArrayList<ArrayList<Card>>();
        for (int i = 0; i < 40; i++) {
            Beruang temp = new Beruang("Beruang", "/img/Hewan/bear.png", 100, 10, "Hewan");
            deck.add(temp);
        }
        for (int i = 0; i < 6; i++) {
            deck_aktif.add(null);
        }
        for (int i = 0; i < 4; i++) {
            ArrayList<Card> temp = new ArrayList<Card>();
            for (int j = 0; j < 5; j++) {
                temp.add(null);
            }
            lahan.add(temp);
        }
    }

    public void add_ciot() {
        card_in_one_turn++;
    }

    public void reset_ciot() {
        card_in_one_turn = 0;
    }

    public void drop_deck_aktif(Card i) {
        for(int x = 0; x < deck_aktif.size(); x++) {
            if(deck_aktif.get(x) != null) {
                deck_aktif.remove(x);
                print_deck_aktif();
                break;
            }
        }
    }

    public void drop_ladang(String i) {
        for(int x = 0; x < lahan.size(); x++) {
            for(int y = 0; y < lahan.get(x).size(); y++) {
                if(lahan.get(x).get(y) != null) {
                    lahan.get(x).remove(y);
                    lahan.get(x).add(this.deck_aktif.get(0));
                }
            }
        }
    }

    public int getCard_in_one_turn() {
        return card_in_one_turn;
    }

    public void remove_deck(String id_kartu) {
        for(int i = 0; i < deck.size(); i++) {
            if(deck.get(i) != null) {
                deck.remove(i);
                break;
            }
        }
    }

    public Card get_kartu(int idx) {
        return deck.get(idx);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void shuffle_to_deck_aktif(Card card) {
        System.out.println("Ini hewan: " + card.getName());
        for(int i = 0; i < deck_aktif.size(); i++) {
            if(deck_aktif.get(i) == null) {
                System.out.println("Imam nunggu gojek");
                deck_aktif.add(i, card);
                // System.out.println(id_kartu);
                break;
            }
        }
    }

    public Card get_deck (int idx) {
        return deck.get(idx);
    }

    public Card get_card_aktif (int idx) {
        return deck_aktif.get(idx);
    }

    public Card get_card_ladang (int i, int j) {
        return lahan.get(i).get(j);
    }

    public String getName() {
        return name;
    }

    public int getCoin() {
        return coin;
    }

    public void add_in_lahan(int x, int y, Card value) {
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
            if (deck_aktif.get(i) == null) {
                System.out.println("null");
                continue;
            } else {
                System.out.println(deck_aktif.get(i).getName());
            }
        }
    }

    public void print_deck() {
        for (int i = 0; i < deck.size(); i++) {
            System.out.println(deck.get(i));
        }
    }

}
