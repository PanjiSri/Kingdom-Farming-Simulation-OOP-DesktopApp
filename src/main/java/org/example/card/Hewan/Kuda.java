package org.example.card.Hewan;

import org.example.card.Produk.DagingKuda;
import org.example.card.Produk.Produk;


public class Kuda extends Hewan {
    public Kuda() {
        super("Kuda", "/img/Hewan/horse.png", 0, 14, "Herbivora");
    }

    public Kuda(int berat)  {
        super("Kuda", "/img/Hewan/horse.png", berat, 14, "Herbivora");
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new DagingKuda();
        } else {
            return null;
        }
    }
    
}
