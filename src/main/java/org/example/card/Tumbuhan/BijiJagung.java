package org.example.card.Tumbuhan;


import org.example.card.Produk.Jagung;
import org.example.card.Produk.Produk;

public class BijiJagung extends Tumbuhan {
    public BijiJagung() {
        super("Biji Jagung", "/img/Hewan/corn seeds.png", 0, 3);
    }

    public BijiJagung(int umur) {
        super("Biji Jagung", "/img/Hewan/corn seeds.png", umur, 3);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.umur = 0;
            return new Jagung();
        } else {
            return null;
        }
    }
}
