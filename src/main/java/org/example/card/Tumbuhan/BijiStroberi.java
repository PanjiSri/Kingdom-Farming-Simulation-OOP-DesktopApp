package org.example.card.Tumbuhan;


import org.example.card.Produk.Produk;
import org.example.card.Produk.Stroberi;

public class BijiStroberi extends Tumbuhan {
    public BijiStroberi() {
        super("Biji Stroberi", "/img/Tanaman/strawberry seeds.png", 0, 4);
    }
    public BijiStroberi(int umur) {
        super("Biji Stroberi", "/img/Hewan/strawberry seeds.png", umur, 4);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.umur = 0;
            return new Stroberi();
        } else {
            return null;
        }
    }
}
