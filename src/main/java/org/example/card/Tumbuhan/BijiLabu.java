package org.example.card.Tumbuhan;


import org.example.card.Produk.Labu;
import org.example.card.Produk.Produk;

public class BijiLabu extends Tumbuhan {
    public BijiLabu() {
        super("Biji Labu", "/img/Tanaman/pumpkin seeds.png", 0, 5);
    }
    
    public BijiLabu(int umur) {
        super("Biji Labu", "/img/Hewan/pumpkin seeds.png", umur, 5);
    }

    @Override
    public Produk panen() {
        if (isSiapPanen()) {
            this.umur = 0;
            return new Labu();
        } else {
            return null;
        }
    }
}
