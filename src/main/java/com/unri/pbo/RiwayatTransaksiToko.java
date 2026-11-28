package com.unri.pbo;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class RiwayatTransaksiToko extends RiwayatTransaksi {
    private String kodeProduk;

    public RiwayatTransaksiToko(String idTransaksi, String jenisTransaksi, String kodeProduk,
                                String namaItem, int jumlah, double totalHarga, String pengguna) {
        super(
            idTransaksi,
            jenisTransaksi,
            "TRANSAKSI BARU",
            namaItem,
            0,
            jumlah,
            BigDecimal.ZERO,
            BigDecimal.valueOf(totalHarga),
            (pengguna == null || pengguna.isBlank()) ? "Admin" : pengguna,
            "Transaksi diproses sistem POS"
        );
        this.kodeProduk = kodeProduk;
    }

    public String getKodeProduk() {
        return kodeProduk;
    }

    public Object[] toTableRow() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new Object[]{
            getIdTransaksi(),
            getWaktu().format(fmt),
            getJenisTransaksi(),
            getDetailAktivitas(),
            getJumlahBaru(),
            DataStore.formatRupiah(getTotalBaru().doubleValue())
        };
    }

    @Override
    public void tampilkanRiwayat() {
        super.tampilkanRiwayat();
        System.out.println("Kode Produk : " + kodeProduk);
    }
}