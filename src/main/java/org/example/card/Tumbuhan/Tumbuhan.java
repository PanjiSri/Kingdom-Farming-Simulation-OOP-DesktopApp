package org.example.card.Tumbuhan;

import java.util.Map;

import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Produk.Produk;

public abstract class Tumbuhan extends Card implements BisaPanen {
    protected int umur;
    protected int standarUmurPanen;
    protected Map<String, Integer> item;
    
    public Tumbuhan(String name, String imgPath, int umur, int standarUmurPanen) {
        super(name, imgPath);
        this.umur = umur;
        this.standarUmurPanen = standarUmurPanen;
        item = Map.of(
            "Accelerate", 0,
            "Delay", 0,
            "Destroy", 0,
            "InstantHarvest", 0,
            "Protect", 0,
            "Trap", 0
        );
    }

    public int getUmur() {
        return umur;
    }

    public int getStandarUmurPanen() {
        return standarUmurPanen;
    }

    public void addUmur(int umur) {
        this.umur += umur;
        if (this.umur < 0) this.umur = 0;
    }

    public boolean isSiapPanen() {
        return this.umur >= this.standarUmurPanen;
    }

    @Override
    public Map<String, Integer> getItem() {
        return item;
    }

    @Override
    abstract public Produk panen();
}
