package org.example.card.Item;

import org.example.card.BisaPanen;

public class InstantHarvest extends Item {
    public InstantHarvest() {
        super("Instant Harvest", "/img/Item/Instant Harvest.png");
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
         bisaPanen.panen();
    }
}
