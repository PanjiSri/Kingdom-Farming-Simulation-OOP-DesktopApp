package org.example.card.Tumbuhan;

import java.util.HashMap;
import java.util.Map;

import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Produk.Produk;

public abstract class Tumbuhan extends Card implements BisaPanen {
    protected int umur;
    protected int standarUmurPanen;
    protected Map<String, Integer> item = new HashMap<>();
    
    public Tumbuhan(String name, String imgPath, int umur, int standarUmurPanen) {
        super(name, imgPath);
        this.umur = umur;
        this.standarUmurPanen = standarUmurPanen;
        item.put("Accelerate", 0);
        item.put("Delay", 0);
        item.put("Destroy", 0);
        item.put("InstantHarvest", 0);
        item.put("Protect", 0);
        item.put("Trap", 0);
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

    abstract public void setImgPathToProduct();

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
