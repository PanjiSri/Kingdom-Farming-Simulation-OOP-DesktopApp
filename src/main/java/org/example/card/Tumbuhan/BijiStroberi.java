package org.example.card.Tumbuhan;


import org.example.card.Produk.Produk;
import org.example.card.Produk.Stroberi;

public class BijiStroberi extends Tumbuhan {
    private String productImg = "/img/Produk/strawberry.png";
    public BijiStroberi() {
        super("Biji Stroberi", "/img/Tanaman/strawberry seeds.png", 0, 4);
    }
    public BijiStroberi(int umur) {
        super("Biji Stroberi", "/img/Hewan/strawberry seeds.png", umur, 4);
    }

    public void setImgPathToProduct() {
        this.imgPath = productImg;
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
