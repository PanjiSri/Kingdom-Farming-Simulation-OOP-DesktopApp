package org.example.card.Item;

import org.example.card.BisaPanen;
import org.example.card.Hewan.Hewan;
import org.example.card.Tumbuhan.Tumbuhan;

public class Accelerate extends Item {
    public Accelerate() {
        super("Accelerate", "/img/Item/Accelerate.png");
    }

    @Override
    public void aksi(BisaPanen bisaPanen) {
        if (bisaPanen instanceof Hewan) {
            ((Hewan) bisaPanen).addBerat(8);
            bisaPanen.setItem("Accelerate", 1);
        } else if (bisaPanen instanceof Tumbuhan) {
            ((Tumbuhan) bisaPanen).addUmur(2);
            bisaPanen.setItem("Accelerate", 1);
        }
    }
}
