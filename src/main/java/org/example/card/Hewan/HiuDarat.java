package org.example.card.Hewan;

import org.example.card.Produk.Produk;
import org.example.card.Produk.SiripHiu;


public class HiuDarat extends Hewan {
    public HiuDarat() {
        super("Hiu Darat", "/img/Hewan/hiu darat.png", 0, 20, "Karnivora");
    }

    public HiuDarat(int berat)  {
        super("Hiu Darat", "/img/Hewan/hiu darat.png", berat, 20, "Karnivora");
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new SiripHiu();
        } else {
            return null;
        }
    }
}
