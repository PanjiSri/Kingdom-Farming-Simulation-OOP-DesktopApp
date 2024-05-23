package org.example.card;

public abstract class Card {
    static public int cardCount = 0;
    protected int id;
    protected String name;
    protected String imgPath;

    public Card(String name, String imgPath) {
        this.id = cardCount++;
        this.name = name;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgPath() {
        return imgPath;
    }
}