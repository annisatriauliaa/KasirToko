package com.unri.pbo;

import java.math.BigDecimal;

public class PenjualanToko extends Penjualan {
    private String kasir;
    private BigDecimal tunai;

    public PenjualanToko(String id, Pelanggan pelanggan, String kasir) {
        super(id, pelanggan);
        this.kasir = (kasir == null || kasir.isBlank()) ? "Kasir" : kasir;
        this.tunai = BigDecimal.ZERO;
    }

    public String getKasir() {
        return kasir;
    }

    public BigDecimal getTunai() {
        return tunai;
    }

    public void setTunai(double nominalTunai) {
        if (nominalTunai < 0) {
            throw new IllegalArgumentException("Nominal tunai tidak boleh negatif.");
        }
        this.tunai = BigDecimal.valueOf(nominalTunai);
    }

    public BigDecimal hitungKembalian() {
        return tunai.subtract(hitungTotal());
    }

    @Override
    public void cetakNota() {
        super.cetakNota();
        System.out.println("Kasir     : " + kasir);
        System.out.println("Tunai     : Rp " + tunai.toPlainString());
        System.out.println("Kembalian : Rp " + hitungKembalian().toPlainString());
    }
}