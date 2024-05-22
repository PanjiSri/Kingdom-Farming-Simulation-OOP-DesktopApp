package org.example.card.Tumbuhan;


import org.example.card.Produk.Jagung;
import org.example.card.Produk.Produk;

public class BijiJagung extends Tumbuhan {
    public BijiJagung(String name, String imgPath, int umur, int standarUmurPanen) {
        super(name, imgPath, umur, standarUmurPanen);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.umur = 0;
            return new Jagung("Jagung", "img/Produk/corn.png", 150, 3);
        } else {
            return null;
        }
    }
}
