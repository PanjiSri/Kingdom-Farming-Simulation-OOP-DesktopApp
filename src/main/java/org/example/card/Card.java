package org.example.card;

public abstract class Card {
    protected String name;
    protected String imgPath;

    public Card(String name, String imgPath) {
        this.name = name;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public String getImgPath() {
        return imgPath;
    }
}