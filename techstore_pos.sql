-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Nov 2026 pada 10.04
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `techstore_pos`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_telepon` varchar(25) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `produk`
--

CREATE TABLE `produk` (
  `kode` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `harga_beli` double NOT NULL,
  `harga_jual` double NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `produk`
--

INSERT INTO `produk` (`kode`, `nama`, `kategori`, `harga_beli`, `harga_jual`, `stok`) VALUES
('PRD-001', 'Laptop ASUS ROG Strix G15', 'Laptop', 14500000, 16000000, 3),
('PRD-002', 'Mouse Logitech G502 Hero', 'Aksesoris', 650000, 850000, 13),
('PRD-003', 'Keyboard Mechanical Keychron K2', 'Aksesoris', 1100000, 1350000, 9),
('PRD-004', 'Kabel USB', 'Aksesoris', 10000, 15000, 9),
('PRD-005', 'Laptop ASUS VivoBook 14 (Core i5, 8GB, 512GB)', 'Laptop', 7650000, 8500000, 10),
('PRD-006', 'Laptop Lenovo Legion 5 (Ryzen 7, 16GB, RTX 4060)', 'Laptop', 17500000, 19200000, 8),
('PRD-007', 'PC Rakitan Office Core i3 Gen 12', 'PC Dekstop', 4100000, 480000, 15),
('PRD-008', 'Prosesor Intel Core i5-12400F', 'Komponen Internal', 1720000, 1950000, 4),
('PRD-009', 'Prosesor AMD Ryzen 5 5600', 'Komponen Internal', 1620000, 1850000, 14),
('PRD-010', 'Router Wi-Fi TP-Link Archer C6 AC1200', 'Jaringan', 340000, 420000, 25),
('PRD-011', 'Kabel LAN UTP Cat6 10 Meter', 'Jaringan', 28000, 45000, 100),
('PRD-012', 'Thermal Paste Arctic MX-4 4g', 'Aksesoris & Perawatan', 65000, 95000, 20),
('PRD-013', 'Kabel HDMI 2.0 High Speed 2 Meter', 'Aksesoris & Perawatan', 32000, 55000, 15);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `id` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_telepon` varchar(25) DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(30) NOT NULL,
  `tanggal` datetime DEFAULT current_timestamp(),
  `jenis` varchar(20) NOT NULL,
  `kode_produk` varchar(20) DEFAULT NULL,
  `nama_item` varchar(100) DEFAULT NULL,
  `jumlah` int(11) NOT NULL,
  `total_harga` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tanggal`, `jenis`, `kode_produk`, `nama_item`, `jumlah`, `total_harga`) VALUES
('TRX-20261125150116', '2026-11-25 15:01:34', 'PENJUALAN', 'PRD-001', 'Laptop ASUS ROG Strix G15', 1, 16000000),
('TRX-20261125152501', '2026-11-25 15:25:20', 'PENJUALAN', 'PRD-004', 'Kabel USB', 3, 45000),
('TRX-20261125152833', '2026-11-25 15:28:49', 'PENJUALAN', 'PRD-004', 'Kabel USB', 5, 75000),
('TRX-20261125153957', '2026-11-25 15:40:20', 'PENJUALAN', 'PRD-002', 'Mouse Logitech G502 Hero', 1, 850000),
('TRX-20261125154032-1', '2026-11-25 15:41:36', 'PENJUALAN', 'PRD-001', 'Laptop ASUS ROG Strix G15', 1, 16000000),
('TRX-20261125154032-2', '2026-11-25 15:41:36', 'PENJUALAN', 'PRD-002', 'Mouse Logitech G502 Hero', 1, 850000),
('TRX-20261125154032-3', '2026-11-25 15:41:36', 'PENJUALAN', 'PRD-003', 'Keyboard Mechanical Keychron K2', 1, 1350000),
('TRX-20261125154032-4', '2026-11-25 15:41:36', 'PENJUALAN', 'PRD-004', 'Kabel USB', 3, 45000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama_lengkap` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `nama_lengkap`) VALUES
(1, 'admin', 'admin', 'Administrator Toko'),
(2, 'kasir', '12345', 'Kasir Utama');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`kode`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
