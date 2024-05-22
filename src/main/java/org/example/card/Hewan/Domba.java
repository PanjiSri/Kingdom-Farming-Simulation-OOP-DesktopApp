package org.example.card.Hewan;

import org.example.card.Produk.DagingDomba;
import org.example.card.Produk.Produk;

public class Domba extends Hewan {
    public Domba(String name, String imgPath, int harga, int standarBeratPanen, String tipe) {
        super(name, imgPath, harga, standarBeratPanen, tipe);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new DagingDomba("Daging Domba", "img/Produk/Daging Domba.png", 120, 6);
        } else {
            return null;
        }
    }
}
