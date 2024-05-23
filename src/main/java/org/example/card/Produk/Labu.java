package org.example.card.Produk;

import org.example.card.BisaPanen;
import org.example.card.Hewan.Hewan;

public class Labu extends Produk {
    public Labu() {
        super("Labu", "/img/Hewan/pumpkin.png", 500, 10);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (bisaPanen instanceof Hewan) {
            if (((Hewan) bisaPanen).getTipe().equals("Omnivora") || ((Hewan) bisaPanen).getTipe().equals("Herbivora")) {
                ((Hewan) bisaPanen).addBerat(getTambahanBerat());
            }
        }
    }
}
