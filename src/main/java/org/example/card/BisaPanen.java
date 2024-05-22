package org.example.card;

import org.example.card.Produk.Produk;

public interface BisaPanen {
    public Produk panen();
    public boolean isTerlindungi();
    public void setTerlindungi(boolean state);
    public boolean isTrapActivated();
    public void setTrapActivated(boolean state);
}
