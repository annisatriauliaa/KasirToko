package com.unri.pbo;

import java.math.BigDecimal;

public class ProdukToko extends Produk {
    private String kategori;

    public ProdukToko(String idProduk, String namaProduk, String kategori, double hargaBeli, double hargaJual, int stok) {
        super(idProduk, namaProduk, BigDecimal.valueOf(hargaBeli), BigDecimal.valueOf(hargaJual), stok);
        this.kategori = (kategori == null || kategori.isBlank()) ? "Umum" : kategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = (kategori == null || kategori.isBlank()) ? "Umum" : kategori;
    }

    @Override
    public void tampilkanProduk() {
        super.tampilkanProduk();
        System.out.println("Kategori : " + kategori);
    }
}