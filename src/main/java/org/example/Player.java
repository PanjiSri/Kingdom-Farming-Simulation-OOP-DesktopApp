package org.example;

import java.util.ArrayList;
import java.util.Collections;
import org.example.card.Card;
import org.example.card.StaticCard;



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
            try {
                Class<?> clazz = Class.forName(StaticCard.getRandomCardName());
                Card temp = (Card) clazz.getDeclaredConstructor().newInstance();
                System.out.println(temp.getImgPath());
                deck.add(temp);
            } catch (Exception e) {
                System.out.println("kelas tidak ditemukan");
                e.printStackTrace();
            }
        }

        System.out.println("===============================");
        print_deck();
        System.out.println("===============================");
        
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
        System.out.println("Belum error");
        String id_card = Integer.toString(i.getId());
        for(int x = 0; x < deck_aktif.size(); x++) {
            String id_card_aktif = Integer.toString(deck_aktif.get(x).getId());
            if(deck_aktif.get(x) != null && id_card_aktif.equals(id_card)) {
                deck_aktif.remove(x);
                deck_aktif.add(x, null);
                print_deck_aktif();
                break;
            }
        }
    }

    public void drop_ladang(String id) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (lahan.get(i).get(j) == null) {
                    continue;
                }
                else {
                    String id_kartu = Integer.toString(lahan.get(i).get(j).getId());
                    if(id_kartu.equals(id)) {
                        lahan.get(i).set(j, null);
                    }
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
                deck_aktif.set(i, card);
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

    public int get_card_aktif_idx (String id) {
        for(int i = 0; i < deck_aktif.size(); i++) {
            if(deck_aktif.get(i) != null) {
                int id_card = deck_aktif.get(i).getId();
                if (Integer.toString(id_card).equals(id)) {
                    return i;
                }
            }
        }
        return -1;
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
        System.out.println(value);
    }

    public void drop_ladang(int x, int y) {
        lahan.get(x).set(y, null);
    }

    public void print_lahan() {
        for (int i = 0; i < lahan.size(); i++) {
            for (int j = 0; j < lahan.get(i).size(); j++) {
                if (lahan.get(i).get(j) == null) {
                    System.out.print("null");
                } else {
                    System.out.print(lahan.get(i).get(j).getId());
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void print_deck_aktif() {
        for (int i = 0; i < deck_aktif.size(); i++) {
            if (deck_aktif.get(i) == null) {
                System.out.println("null");
            } else {
                System.out.println(deck_aktif.get(i).getId());
            }
        }
        System.out.println();
    }

    public void print_deck() {
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) == null) {
                System.out.println("null");
            } else {
                System.out.println(deck.get(i).getId() + " " + deck.get(i).getName());
            }
        }
    }

    public int get_deck_aktif_size() {
        int size = 0;
        for (int i = 0; i < 6; i++) {
            if (deck_aktif.get(i) != null) {
                size+=1;
            }
        }
        return size;
    }

    public ArrayList<Integer> get_idx_lahan(String id) {
        ArrayList<Integer> idx_lahan = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (lahan.get(i).get(j) == null) {
                    continue;
                }
                else {
                    String id_kartu = Integer.toString(lahan.get(i).get(j).getId());
                    if(id_kartu.equals(id)) {
                        idx_lahan.add(i);
                        idx_lahan.add(j);
                    }
                    System.out.println("Baris: " + idx_lahan.get(0));
                    return idx_lahan;
                }
            }
        }
        return null;
    }

    public void player_load(ArrayList<String> data) {
        coin = Integer.valueOf(data.get(0));
        int deck_size = Integer.valueOf(data.get(1));
        for(int i = 0; i < deck_size; i++) {
            try {
                Class<?> clazz = Class.forName(StaticCard.getRandomCardName());
                Card temp = (Card) clazz.getDeclaredConstructor().newInstance();
                System.out.println(temp.getImgPath());
                deck.add(temp);
            } catch (Exception e) {
                System.out.println("kelas tidak ditemukan");
                e.printStackTrace();
            }
        }
    }

    public int deck_size() {
        int size = 0;
        for(int i = 0; i < deck.size(); i++) {

        }
    }

}
