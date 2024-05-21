package org.example.card.Tumbuhan;


import org.example.card.Produk.Labu;
import org.example.card.Produk.Produk;

public class BijiLabu extends Tumbuhan {
    public BijiLabu(String name, String imgPath, int umur, int standarUmurPanen) {
        super(name, imgPath, umur, standarUmurPanen);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.umur = 0;
            return new Labu("Labu", "img/Produk/pumpkin.png", 500, 10);
        } else {
            return null;
        }
    }
}
