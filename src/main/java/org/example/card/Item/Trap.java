package org.example.card.Item;

import org.example.card.BisaPanen;

public class Trap extends Item {
    public Trap() {
        super("Trap", "/img/Hewan/bear trap.png");
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (!bisaPanen.isTrapActivated()) {
            bisaPanen.setTrapActivated(false);
        }
    }
}
