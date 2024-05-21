package org.example.card.Item;

import org.example.card.BisaPanen;

public class Destroy extends Item {
    public Destroy(String name, String imgPath) {
        super(name, imgPath);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (!bisaPanen.isTerlindungi()) {
            // Destroy the card
        }
    }
}
