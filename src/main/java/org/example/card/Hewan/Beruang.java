package org.example.card.Hewan;

import org.example.card.Produk.DagingBeruang;
import org.example.card.Produk.Produk;

public class Beruang extends Hewan {
    public Beruang(String name, String imgPath, int harga, int standarBeratPanen, String tipe) {
        super(name, imgPath, harga, standarBeratPanen, tipe);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new DagingBeruang("Daging Beruang", "img/Produk/Daging Beruang.png", 500, 12);
        } else {
            return null;
        }
    }
}
