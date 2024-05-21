package org.example.card.Tumbuhan;


import org.example.card.Produk.Produk;
import org.example.card.Produk.Stroberi;

public class BijiStroberi extends Tumbuhan {
    public BijiStroberi(String name, String imgPath, int umur, int standarUmurPanen) {
        super(name, imgPath, umur, standarUmurPanen);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.umur = 0;
            return new Stroberi("Stroberi", "img/Produk/strawberry.png", 350, 5);
        } else {
            return null;
        }
    }
}
