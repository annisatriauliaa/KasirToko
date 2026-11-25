package com.unri.pbo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DataStore {

    // ================= KONFIGURASI DATABASE MYSQL =================
    private static final String URL = "jdbc:mysql://localhost:3306/db_techstore_pos";
    private static final String USER = "root";
    private static final String PASS = ""; // Kosongkan jika memakai XAMPP default

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ================= MODEL DATA (TETAP SAMA SEPERTI SEBELUMNYA) =================
    public static class Produk {
        public String kode;
        public String nama;
        public String kategori;
        public double hargaBeli;
        public double hargaJual;
        public int stok;

        public Produk(String kode, String nama, String kategori, double hargaBeli, double hargaJual, int stok) {
            this.kode = kode;
            this.nama = nama;
            this.kategori = kategori;
            this.hargaBeli = hargaBeli;
            this.hargaJual = hargaJual;
            this.stok = stok;
        }
    }

    public static class Transaksi {
        public String idTransaksi;
        public String tanggal;
        public String jenis; // "PENJUALAN" atau "PEMBELIAN"
        public String namaItem;
        public int jumlah;
        public double totalHarga;

        public Transaksi(String idTransaksi, String tanggal, String jenis, String namaItem, int jumlah, double totalHarga) {
            this.idTransaksi = idTransaksi;
            this.tanggal = tanggal;
            this.jenis = jenis;
            this.namaItem = namaItem;
            this.jumlah = jumlah;
            this.totalHarga = totalHarga;
        }
    }

    // ================= PENYIMPANAN DATA (SINKRON DENGAN MYSQL) =================
    public static List<Produk> daftarProduk = new ArrayList<>();
    public static List<Transaksi> daftarRiwayat = new ArrayList<>();
    public static String currentUser = "";

    // ================= 1. AUTENTIKASI LOGIN =================
    public static boolean validasiLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    currentUser = rs.getString("nama_lengkap");
                    loadProdukFromDB();   // Langsung muat data produk saat login berhasil
                    loadRiwayatFromDB();  // Langsung muat riwayat transaksi
                    return true;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Gagal terhubung ke MySQL:\n" + e.getMessage(), 
                "Error Database", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // ================= 2. OPERASI PRODUK (CRUD) =================
    public static void loadProdukFromDB() {
        daftarProduk.clear();
        String sql = "SELECT * FROM produk ORDER BY kode ASC";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                daftarProduk.add(new Produk(
                    rs.getString("kode"),
                    rs.getString("nama"),
                    rs.getString("kategori"),
                    rs.getDouble("harga_beli"),
                    rs.getDouble("harga_jual"),
                    rs.getInt("stok")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean tambahProduk(Produk p) {
        String sql = "INSERT INTO produk (kode, nama, kategori, harga_beli, harga_jual, stok) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, p.kode);
            pst.setString(2, p.nama);
            pst.setString(3, p.kategori);
            pst.setDouble(4, p.hargaBeli);
            pst.setDouble(5, p.hargaJual);
            pst.setInt(6, p.stok);
            pst.executeUpdate();
            
            loadProdukFromDB(); // Refresh list lokal
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menambah produk: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateProduk(Produk p) {
        String sql = "UPDATE produk SET nama=?, kategori=?, harga_beli=?, harga_jual=?, stok=? WHERE kode=?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, p.nama);
            pst.setString(2, p.kategori);
            pst.setDouble(3, p.hargaBeli);
            pst.setDouble(4, p.hargaJual);
            pst.setInt(5, p.stok);
            pst.setString(6, p.kode);
            pst.executeUpdate();
            
            loadProdukFromDB();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengubah produk: " + e.getMessage());
            return false;
        }
    }

    public static boolean hapusProduk(String kode) {
        String sql = "DELETE FROM produk WHERE kode=?";
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, kode);
            pst.executeUpdate();
            
            loadProdukFromDB();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus produk: " + e.getMessage());
            return false;
        }
    }

    // ================= 3. TRANSAKSI PENJUALAN & PEMBELIAN =================
    public static boolean prosesTransaksi(String idTrx, String jenis, String kodeProduk, String namaItem, int jumlah, double totalHarga) {
        String sqlInsertTrx = "INSERT INTO transaksi (id_transaksi, jenis, kode_produk, nama_item, jumlah, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
        // Jika PENJUALAN stok berkurang (-), jika PEMBELIAN stok bertambah (+)
        String sqlUpdateStok = jenis.equals("PENJUALAN") 
                ? "UPDATE produk SET stok = stok - ? WHERE kode = ?"
                : "UPDATE produk SET stok = stok + ? WHERE kode = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false); // Mulai transaksi database agar aman
            
            try (PreparedStatement pstTrx = conn.prepareStatement(sqlInsertTrx);
                 PreparedStatement pstStok = conn.prepareStatement(sqlUpdateStok)) {
                
                // Simpan ke tabel transaksi
                pstTrx.setString(1, idTrx);
                pstTrx.setString(2, jenis);
                pstTrx.setString(3, kodeProduk);
                pstTrx.setString(4, namaItem);
                pstTrx.setInt(5, jumlah);
                pstTrx.setDouble(6, totalHarga);
                pstTrx.executeUpdate();

                // Update stok di tabel produk
                pstStok.setInt(1, jumlah);
                pstStok.setString(2, kodeProduk);
                pstStok.executeUpdate();

                conn.commit(); // Simpan permanen
                loadProdukFromDB();
                loadRiwayatFromDB();
                return true;
            } catch (SQLException ex) {
                conn.rollback(); // Batalkan jika ada error di tengah jalan
                throw ex;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Transaksi Gagal: " + e.getMessage());
            return false;
        }
    }

    // ================= 4. LOAD RIWAYAT TRANSAKSI =================
    public static void loadRiwayatFromDB() {
        daftarRiwayat.clear();
        String sql = "SELECT * FROM transaksi ORDER BY tanggal DESC";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                daftarRiwayat.add(new Transaksi(
                    rs.getString("id_transaksi"),
                    rs.getString("tanggal"),
                    rs.getString("jenis"),
                    rs.getString("nama_item"),
                    rs.getInt("jumlah"),
                    rs.getDouble("total_harga")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}