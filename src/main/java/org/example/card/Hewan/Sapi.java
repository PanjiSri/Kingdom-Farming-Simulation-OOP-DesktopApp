package org.example.card.Hewan;

import org.example.card.Produk.Produk;
import org.example.card.Produk.Susu;


public class Sapi extends Hewan {
    public Sapi(String name, String imgPath, int harga, int standarBeratPanen, String tipe) {
        super(name, imgPath, harga, standarBeratPanen, tipe);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new Susu("Susu Sapi", "img/Produk/susu.png", 100, 4);
        } else {
            return null;
        }
    }
}
