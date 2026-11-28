/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.unri.pbo;

public class PanelSupplier extends javax.swing.JPanel {

    private boolean modeEdit = false;

   public PanelSupplier() {
        initComponents();
        jLabel1.setText("Daftar Supplier");
        resetForm();
        tampilData("");

        if (!"admin".equalsIgnoreCase(DataStore.currentRole)) {
            jButton10.setEnabled(false);
            jButton7.setEnabled(false);
        }

        jTextField13.putClientProperty("JTextField.placeholderText", "Cari ID, nama supplier, atau no. telepon...");
        jTextField13.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { tampilData(jTextField13.getText()); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { tampilData(jTextField13.getText()); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { tampilData(jTextField13.getText()); }
        });
    }

    private void tampilData(String keyword) {
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        String sql = "SELECT * FROM supplier WHERE id LIKE ? OR nama LIKE ? OR no_telepon LIKE ? ORDER BY id ASC";
        try (java.sql.Connection conn = DataStore.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            String cari = "%" + keyword.trim() + "%";
            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                int no = 1;
                while (rs.next()) {
                    SupplierToko s = new SupplierToko(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("no_telepon"),
                        "Distributor",
                        "Elektronik"
                    );

                    model.addRow(new Object[]{
                        no++,
                        s.getId(),
                        s.getNama(),
                        s.getNoTelepon(),
                        s.getAlamat()
                    });
                }
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal memuat data supplier: " + e.getMessage());
        }
    }

    private void resetForm() {
        String idOtomatis = "SUP-" + new java.text.SimpleDateFormat("mmss").format(new java.util.Date());
        jTextField6.setText(idOtomatis);
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField6.setEditable(true);
        modeEdit = false;
        jLabel7.setText("Tambah / Edit Supplier");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField13 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setBackground(new java.awt.Color(241, 245, 249));
        setPreferredSize(new java.awt.Dimension(1200, 650));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Tambah / Edit Supplier");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("ID Supplier");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Nama Supplier");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("No. Telepon");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Alamat");

        jButton6.setBackground(new java.awt.Color(102, 153, 255));
        jButton6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Simpan");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(204, 204, 204));
        jButton9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton9.setText("Batal");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(275, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(1200, 650));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Daftar Pelanggan");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No. ", "ID Supplier", "Nama Supplier", "No. Telepon", "Alamat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);
        }

        jButton7.setBackground(new java.awt.Color(255, 51, 51));
        jButton7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Hapus");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(102, 153, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Edit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jButton10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton7))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(196, 196, 196))))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(247, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    String id = jTextField6.getText().trim();
        String nama = jTextField7.getText().trim();
        String telp = jTextField8.getText().trim();
        String alamat = jTextField9.getText().trim();

        if (id.isEmpty() || nama.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "ID Supplier dan Nama Supplier wajib diisi!", "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        SupplierToko supplierObj;
        try {
            supplierObj = new SupplierToko(id, nama, alamat, telp, "Distributor", "Elektronik");
            supplierObj.tampilkanData(); // Menampilkan log OOP ke console NetBeans
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Tidak Valid", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (modeEdit && !"admin".equalsIgnoreCase(DataStore.currentRole)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Akses ditolak! Hanya Admin yang dapat mengedit supplier.");
            return;
        }

        String sql = modeEdit
                ? "UPDATE supplier SET nama=?, no_telepon=?, alamat=? WHERE id=?"
                : "INSERT INTO supplier (id, nama, no_telepon, alamat) VALUES (?, ?, ?, ?)";

        try (java.sql.Connection conn = DataStore.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            if (modeEdit) {
                ps.setString(1, supplierObj.getNama());
                ps.setString(2, supplierObj.getNoTelepon());
                ps.setString(3, supplierObj.getAlamat());
                ps.setString(4, supplierObj.getId());
            } else {
                ps.setString(1, supplierObj.getId());
                ps.setString(2, supplierObj.getNama());
                ps.setString(3, supplierObj.getNoTelepon());
                ps.setString(4, supplierObj.getAlamat());
            }

            ps.executeUpdate();
            javax.swing.JOptionPane.showMessageDialog(this, modeEdit ? "Data supplier berhasil diperbarui!" : "Supplier baru berhasil ditambahkan!");
            resetForm();
            tampilData("");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal menyimpan data supplier: " + e.getMessage(), "Error Database", javax.swing.JOptionPane.ERROR_MESSAGE);
        } // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    if (!"admin".equalsIgnoreCase(DataStore.currentRole)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Akses ditolak! Hanya Admin yang dapat menghapus supplier.");
            return;
        }

        int baris = jTable1.getSelectedRow();
        if (baris == -1 || jTable1.getValueAt(baris, 1) == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih baris supplier di tabel yang ingin dihapus!", "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String id = jTable1.getValueAt(baris, 1).toString();
        String nama = jTable1.getValueAt(baris, 2).toString();

        int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus supplier \"" + nama + "\" (" + id + ")?",
                "Konfirmasi Hapus", javax.swing.JOptionPane.YES_NO_OPTION);

        if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
            try (java.sql.Connection conn = DataStore.getConnection();
                 java.sql.PreparedStatement ps = conn.prepareStatement("DELETE FROM supplier WHERE id=?")) {
                ps.setString(1, id);
                ps.executeUpdate();
                javax.swing.JOptionPane.showMessageDialog(this, "Supplier berhasil dihapus!");
                resetForm();
                tampilData("");
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Gagal menghapus supplier: " + e.getMessage());
            }
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    resetForm();    // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    if (!"admin".equalsIgnoreCase(DataStore.currentRole)) {
            javax.swing.JOptionPane.showMessageDialog(this, "Akses ditolak! Hanya Admin yang dapat mengedit supplier.");
            return;
        }

        int baris = jTable1.getSelectedRow();
        if (baris == -1 || jTable1.getValueAt(baris, 1) == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Pilih baris supplier di tabel yang ingin diedit!", "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        jTextField6.setText(jTable1.getValueAt(baris, 1).toString());
        jTextField7.setText(jTable1.getValueAt(baris, 2).toString());
        jTextField8.setText(jTable1.getValueAt(baris, 3) != null ? jTable1.getValueAt(baris, 3).toString() : "");
        jTextField9.setText(jTable1.getValueAt(baris, 4) != null ? jTable1.getValueAt(baris, 4).toString() : "");

        jTextField6.setEditable(false);
        modeEdit = true;
        jLabel7.setText("Edit Supplier (" + jTextField6.getText() + ")");    // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
