package org.example.card.Item;

import org.example.card.BisaPanen;

public class Protect extends Item {
    public Protect(String name, String imgPath) {
        super(name, imgPath);
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (!bisaPanen.isTerlindungi()) {
            bisaPanen.setTerlindungi(true);
        }
    }
}
