package org.example.card.Hewan;

import org.example.card.Produk.DagingKuda;
import org.example.card.Produk.Produk;


public class Kuda extends Hewan {
    public Kuda(String name, String imgPath, int harga, int standarBeratPanen, String tipe) {
        super(name, imgPath, harga, standarBeratPanen, tipe);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new DagingKuda("Daging Kuda", "img/Produk/Daging Kuda.png", 150, 8);
        } else {
            return null;
        }
    }
    
}
