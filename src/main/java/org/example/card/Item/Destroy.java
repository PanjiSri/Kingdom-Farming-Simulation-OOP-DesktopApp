package org.example.card.Item;

import org.example.card.BisaPanen;

public class Destroy extends Item {
    public Destroy() {
        super("Destroy", "/img/Item/Destroy.png");
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (!bisaPanen.isTerlindungi()) {
            // Destroy the card
        }
    }
}
