package org.example.card.Item;

import org.example.card.BisaDigunakan;
import org.example.card.BisaPanen;
import org.example.card.Card;

public abstract class Item extends Card implements BisaDigunakan {
    public Item(String name, String imgPath) {
        super(name, imgPath);
    }

    @Override
    public abstract void aksi(BisaPanen bisaPanen);
}