package org.example.card.Hewan;

import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Item.Item;
import org.example.card.Produk.Produk;

import java.util.HashMap;
import java.util.Map;

public abstract class Hewan extends Card implements BisaPanen {
    protected int berat;
    protected int standarBeratPanen;
    protected String tipe;
    protected Map<String, Integer> item = new HashMap<>();
    
    public Hewan(String name, String imgPath, int berat, int standarBeratPanen, String tipe) {
        super(name, imgPath);
        this.berat = berat;
        this.standarBeratPanen = standarBeratPanen;
        this.tipe = tipe;
        item.put("Accelerate", 0);
        item.put("Delay", 0);
        item.put("Destroy", 0);
        item.put("InstantHarvest", 0);
        item.put("Protect", 0);
        item.put("Trap", 0);
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
    public void setItem(String key, Integer value) {
        int oldValue = item.get(key);
        int newValue = oldValue + value;
        if (newValue < 0) newValue = 0;
        item.put(key, newValue);
    }

    @Override
    abstract public Produk panen();
}
