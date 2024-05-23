package org.example.card.Hewan;

import org.example.card.Produk.DagingBeruang;
import org.example.card.Produk.Produk;

public class Beruang extends Hewan {
    public Beruang() {
        super("Beruang", "/img/Hewan/bear.png", 0, 25, "Omnivora");
    }

    public Beruang(int berat) {
        super("Beruang", "/img/Hewan/bear.png", berat, 25, "Omnivora");
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new DagingBeruang();
        } else {
            return null;
        }
    }
}
