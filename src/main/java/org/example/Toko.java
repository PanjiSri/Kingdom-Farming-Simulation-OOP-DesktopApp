package org.example;

import java.util.HashMap;
import java.util.Map;

public class Toko {
    private final Map<String, Integer> stok;

    // untuk start tanpa file
    public Toko() {
        stok = new HashMap<>();
        stok.put("SIRIP_HIU", 0);
        stok.put("SUSU", 0);
        stok.put("DAGING_DOMBA", 0);
        stok.put("DAGING_KUDA", 0);
        stok.put("TELUR", 0);
        stok.put("DAGING_BERUANG", 0);
        stok.put("JAGUNG", 0);
        stok.put("LABU", 0);
        stok.put("STROBERI", 0);
    }

    // untuk load dari file
    public Toko(Map<String, Integer> stok) {
        this.stok = stok;
    }

    // untuk GUI
    public Map<String, Integer> getStok() {
        return stok;
    }

    // ambil stok produk
    public int ambilStokProduk(String namaProduk) {
        return stok.get(namaProduk);
    }

    // stok produk tambah 1
    public void tambahStokProduk(String namaProduk) {
        stok.put(namaProduk, ambilStokProduk(namaProduk) + 1);
    }

    // stok produk kurangi 1
    public void kurangiStokProduk(String namaProduk) {
        if (ambilStokProduk(namaProduk) == 0) {
            throw new IllegalStateException("Stok " + namaProduk + " kosong");
        }
        stok.put(namaProduk, ambilStokProduk(namaProduk) - 1);
    }
}
