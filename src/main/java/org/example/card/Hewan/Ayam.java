package org.example.card.Hewan;

import org.example.card.Produk.Produk;
import org.example.card.Produk.Telur;

public class Ayam extends Hewan {
    public Ayam() {
        super("Ayam", "/img/Hewan/chicken.png", 0, 5, "Omnivora");
    }

    public Ayam(int berat) {
        super("Ayam", "/img/Hewan/chicken.png", berat, 5, "Omnivora");
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new Telur();
        } else {
            return null;
        }
    }
}
