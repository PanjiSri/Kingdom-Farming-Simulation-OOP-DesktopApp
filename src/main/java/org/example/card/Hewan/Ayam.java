package org.example.card.Hewan;

import org.example.card.Produk.Produk;
import org.example.card.Produk.Telur;

public class Ayam extends Hewan {
    public Ayam(String name, String imgPath, int harga, int standarBeratPanen, String tipe) {
        super(name, imgPath, harga, standarBeratPanen, tipe);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new Telur("Telur", "img/Produk/telur.png", 50, 2);
        } else {
            return null;
        }
    }
}
