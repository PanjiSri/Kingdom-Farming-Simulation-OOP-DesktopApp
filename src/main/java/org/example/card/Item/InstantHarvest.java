package org.example.card.Item;

import org.example.card.BisaPanen;
import org.example.card.Hewan.Hewan;
import org.example.card.Tumbuhan.Tumbuhan;

public class InstantHarvest extends Item {
    public InstantHarvest() {
        super("Instant Harvest", "/img/Item/Instant Harvest.png");
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (bisaPanen instanceof Hewan) {
            ((Hewan) bisaPanen).addBerat(1000000000);
        } else if (bisaPanen instanceof Tumbuhan) {
            ((Tumbuhan) bisaPanen).addUmur(1000000000);
        }
    }
}
