package org.example.card.Tumbuhan;

import org.example.card.BisaPanen;
import org.example.card.Card;
import org.example.card.Produk.Produk;

public abstract class Tumbuhan extends Card implements BisaPanen {
    protected int umur;
    protected int standarUmurPanen;
    protected boolean isTerlindungi = false;
    protected boolean isTrapActivated = false;
    
    public Tumbuhan(String name, String imgPath, int umur, int standarUmurPanen) {
        super(name, imgPath);
        this.umur = umur;
        this.standarUmurPanen = standarUmurPanen;
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
    public boolean isTerlindungi() {
        return this.isTerlindungi;
    }

    @Override
    public void setTerlindungi(boolean state) {
        this.isTerlindungi = state;
    }

    @Override
    public boolean isTrapActivated() {
        return this.isTrapActivated;
    }

    @Override
    public void setTrapActivated(boolean state) {
        this.isTrapActivated = state;
    }

    @Override
    abstract public Produk panen();
}
