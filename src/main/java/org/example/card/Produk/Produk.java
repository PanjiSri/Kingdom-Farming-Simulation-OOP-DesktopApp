package org.example.card.Produk;

import org.example.card.BisaDigunakan;
import org.example.card.BisaPanen;
import org.example.card.Card;

public abstract class Produk extends Card implements BisaDigunakan {
    protected int harga;
    protected int tambahanBerat;

    public Produk(String name, String imgPath, int harga, int tambahanBerat) {
        super(name, imgPath);
        this.harga = harga;
        this.tambahanBerat = tambahanBerat;
    }

    public int getHarga() {
        return harga;
    }

    public int getTambahanBerat() {
        return tambahanBerat;
    }

    @Override
    public abstract void aksi(BisaPanen bisaPanen);
}
