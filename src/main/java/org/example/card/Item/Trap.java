package org.example.card.Item;

import org.example.card.BisaPanen;

public class Trap extends Item {
    public Trap(String name, String imgPath) {
        super(name, imgPath);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (!bisaPanen.isTrapActivated()) {
            bisaPanen.setTrapActivated(false);
        }
    }
}
