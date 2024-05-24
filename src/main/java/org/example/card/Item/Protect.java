package org.example.card.Item;

import org.example.card.BisaPanen;

public class Protect extends Item {
    public Protect() {
        super("Protect", "/img/Item/Protect.png");
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
       bisaPanen.setItem("Protect", 1);
    }
}
