package org.example;

import java.util.HashMap;
import java.util.Map;

public class Toko {
    private final Map<String, Integer> stok;
    private final Map<String, Integer> harga;

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
        harga = new HashMap<>();
        harga.put("SIRIP_HIU", 500);
        harga.put("SUSU", 100);
        harga.put("DAGING_DOMBA", 120);
        harga.put("DAGING_KUDA", 150);
        harga.put("TELUR", 50);
        harga.put("DAGING_BERUANG", 500);
        harga.put("JAGUNG", 150);
        harga.put("LABU", 500);
        harga.put("STROBERI", 350);
    }

    // untuk load dari file
    public Toko(Map<String, Integer> stok) {
        this.stok = stok;
        harga = new HashMap<>();
        harga.put("SIRIP_HIU", 500);
        harga.put("SUSU", 100);
        harga.put("DAGING_DOMBA", 120);
        harga.put("DAGING_KUDA", 150);
        harga.put("TELUR", 50);
        harga.put("DAGING_BERUANG", 500);
        harga.put("JAGUNG", 150);
        harga.put("LABU", 500);
        harga.put("STROBERI", 350);
    }

    // untuk GUI
    public Map<String, Integer> getStok() {
        return stok;
    }
    public Map<String, Integer> getHarga() {
        return harga;
    }

    // ambil stok dan harga produk
    public int ambilStokProduk(String namaProduk) {
        return stok.get(namaProduk);
    }
    public int ambilHargaProduk(String namaProduk) {
        return harga.get(namaProduk);
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

    // set stok produk
    public void setStokProduk(String namaProduk, int stokProduk) {
        stok.put(namaProduk, stokProduk);
    }
}
