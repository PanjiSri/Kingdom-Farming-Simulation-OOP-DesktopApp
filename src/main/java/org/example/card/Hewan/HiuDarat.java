package org.example.card.Hewan;

import org.example.card.Produk.Produk;
import org.example.card.Produk.SiripHiu;


public class HiuDarat extends Hewan {
    public HiuDarat(String name, String imgPath, int harga, int standarBeratPanen, String tipe) {
        super(name, imgPath, harga, standarBeratPanen, tipe);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new SiripHiu("Sirip Hiu", "img/Produk/shark-fin.png", 500, 12);
        } else {
            return null;
        }
    }
}
