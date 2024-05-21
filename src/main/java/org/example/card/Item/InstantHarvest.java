package org.example.card.Item;

import org.example.card.BisaPanen;

public class InstantHarvest extends Item {
    public InstantHarvest(String name, String imgPath) {
        super(name, imgPath);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
         bisaPanen.panen();
    }
}
