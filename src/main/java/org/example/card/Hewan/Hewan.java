package org.example.card.Hewan;

import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Produk.Produk;
import java.util.Map;

public abstract class Hewan extends Card implements BisaPanen {
    protected int berat;
    protected int standarBeratPanen;
    protected String tipe;
    protected Map<String, Integer> item;
    
    public Hewan(String name, String imgPath, int berat, int standarBeratPanen, String tipe) {
        super(name, imgPath);
        this.berat = berat;
        this.standarBeratPanen = standarBeratPanen;
        this.tipe = tipe;
        item = Map.of(
            "Accelerate", 0,
            "Delay", 0,
            "Destroy", 0,
            "InstantHarvest", 0,
            "Protect", 0,
            "Trap", 0
        );
    }

    public int getBerat() {
        return berat;
    }

    public int getStandarBeratPanen() {
        return standarBeratPanen;
    }

    public String getTipe() {
        return tipe;
    }

    public void addBerat(int berat) {
        System.out.println("Test: " + this.berat);
        System.out.println("Test: " + berat);
        this.berat += berat;
        if (this.berat < 0) this.berat = 0;
    }

    public boolean isSiapPanen() {
        return this.berat >= this.standarBeratPanen;
    }

    @Override
    public Map<String, Integer> getItem() {
        return item;
    }

    @Override
    abstract public Produk panen();
}
