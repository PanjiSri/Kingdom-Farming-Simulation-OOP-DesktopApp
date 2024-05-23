package org.example.card.Produk;

import org.example.card.BisaPanen;
import org.example.card.Hewan.Hewan;

public class DagingKuda extends Produk {
    public DagingKuda() {
        super("Daging Kuda", "/img/Hewan/Daging Kuda.png", 150, 8);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (bisaPanen instanceof Hewan) {
            if (((Hewan) bisaPanen).getTipe().equals("Omnivora") || ((Hewan) bisaPanen).getTipe().equals("Karnivora")) {
                ((Hewan) bisaPanen).addBerat(getTambahanBerat());
            }
        }
    }
}
