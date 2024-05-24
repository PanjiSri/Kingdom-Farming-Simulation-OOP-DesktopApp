package org.example.card.Tumbuhan;


import org.example.card.Produk.Labu;
import org.example.card.Produk.Produk;

public class BijiLabu extends Tumbuhan {
    private String productImg = "/img/Produk/pumpkin.png";
    public BijiLabu() {
        super("Biji Labu", "/img/Tanaman/pumpkin seeds.png", 0, 5);
    }
    
    public BijiLabu(int umur) {
        super("Biji Labu", "/img/Hewan/pumpkin seeds.png", umur, 5);
    }

    public void setImgPathToProduct() {
        this.imgPath = productImg;
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
