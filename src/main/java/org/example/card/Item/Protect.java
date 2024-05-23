package org.example.card.Item;

import org.example.card.BisaPanen;

public class Protect extends Item {
    public Protect() {
        super("Protect", "/img/Hewan/Protect.png");
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (!bisaPanen.isTerlindungi()) {
            bisaPanen.setTerlindungi(true);
        }
    }
}
