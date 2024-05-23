package org.example.card.Hewan;

import org.example.card.Produk.DagingDomba;
import org.example.card.Produk.Produk;

public class Domba extends Hewan {
    public Domba() {
        super("Domba", "/img/Hewan/sheep.png", 0, 12, "Herbivora");
    }
    
    public Domba(int berat) {
        super("Domba", "/img/Hewan/sheep.png", berat, 12, "Herbivora");
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.berat = 0;
            return new DagingDomba();
        } else {
            return null;
        }
    }
}
