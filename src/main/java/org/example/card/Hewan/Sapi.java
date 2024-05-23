package org.example.card.Hewan;

import org.example.card.Produk.Produk;
import org.example.card.Produk.Susu;


public class Sapi extends Hewan {
    public Sapi() {
        super("Sapi", "/img/Hewan/cow.png", 0, 10, "Herbivora");
    }
    
    public Sapi(int berat)  {
        super("Sapi", "/img/Hewan/cow.png", berat, 10, "Herbivora");
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new Susu();
        } else {
            return null;
        }
    }
}
