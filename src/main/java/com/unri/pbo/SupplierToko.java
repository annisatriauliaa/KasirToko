package com.unri.pbo;

public class SupplierToko extends Supplier {
    private String kategoriPasokan;

    public SupplierToko(String id, String nama, String alamat, String noTelepon, String namaPerusahaan, String kategoriPasokan) {
        super(id, nama, alamat, noTelepon, namaPerusahaan);
        this.kategoriPasokan = (kategoriPasokan == null || kategoriPasokan.isBlank()) ? "Umum" : kategoriPasokan;
    }

    public String getKategoriPasokan() {
        return kategoriPasokan;
    }

    public void setKategoriPasokan(String kategoriPasokan) {
        this.kategoriPasokan = (kategoriPasokan == null || kategoriPasokan.isBlank()) ? "Umum" : kategoriPasokan;
    }

    public Object[] toTableRow() {
        return new Object[]{
            getId(),
            getNama(),
            getNoTelepon(),
            getAlamat()
        };
    }

    @Override
    public void tampilkanData() {
        super.tampilkanData();
        System.out.println("Kategori Pasokan : " + kategoriPasokan);
    }
}