package org.example.card.Item;

import org.example.card.BisaPanen;

public class Trap extends Item {
    public Trap() {
        super("Trap", "/img/Item/bear trap.png");
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        bisaPanen.getItem().put("Trap", bisaPanen.getItem().get("Trap") + 1);
    }
}
