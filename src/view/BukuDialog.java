package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BukuDialog extends JDialog {
    private JTextField tfJudul, tfPenulis, tfTahun, tfKategori, tfISBN, tfStok;
    private JButton btnSimpan, btnBatal;
    private boolean confirmed = false;
    
    public BukuDialog(Frame parent, String title) {
        super(parent, title, true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
        setSize(400, 300);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel input
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new JLabel("Judul:"));
        tfJudul = new JTextField();
        inputPanel.add(tfJudul);
        
        inputPanel.add(new JLabel("Penulis:"));
        tfPenulis = new JTextField();
        inputPanel.add(tfPenulis);
        
        inputPanel.add(new JLabel("Tahun Terbit:"));
        tfTahun = new JTextField();
        inputPanel.add(tfTahun);
        
        inputPanel.add(new JLabel("Kategori:"));
        tfKategori = new JTextField();
        inputPanel.add(tfKategori);
        
        inputPanel.add(new JLabel("ISBN:"));
        tfISBN = new JTextField();
        inputPanel.add(tfISBN);
        
        inputPanel.add(new JLabel("Stok:"));
        tfStok = new JTextField();
        inputPanel.add(tfStok);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSimpan = new JButton("Simpan");
        btnBatal = new JButton("Batal");
        
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnBatal);
        
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Event listeners
        btnSimpan.addActionListener(this::handleSimpan);
        btnBatal.addActionListener(e -> {
            confirmed = false;
            dispose();
        });
        
        // Enter key untuk simpan
        getRootPane().setDefaultButton(btnSimpan);
    }
    
    private void handleSimpan(ActionEvent e) {
        if (validateInput()) {
            confirmed = true;
            dispose();
        }
    }
    
    private boolean validateInput() {
        if (tfJudul.getText().trim().isEmpty()) {
            showError("Judul harus diisi!");
            tfJudul.requestFocus();
            return false;
        }
        
        if (tfPenulis.getText().trim().isEmpty()) {
            showError("Penulis harus diisi!");
            tfPenulis.requestFocus();
            return false;
        }
        
        try {
            int tahun = Integer.parseInt(tfTahun.getText());
            if (tahun < 1900 || tahun > 2100) {
                showError("Tahun terbit harus antara 1900-2100");
                tfTahun.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Tahun terbit harus angka!");
            tfTahun.requestFocus();
            return false;
        }
        
        try {
            int stok = Integer.parseInt(tfStok.getText());
            if (stok < 0) {
                showError("Stok tidak boleh negatif!");
                tfStok.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Stok harus angka!");
            tfStok.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public boolean isConfirmed() { return confirmed; }
    public String getJudul() { return tfJudul.getText().trim(); }
    public String getPenulis() { return tfPenulis.getText().trim(); }
    public int getTahun() { return Integer.parseInt(tfTahun.getText()); }
    public String getKategori() { return tfKategori.getText().trim(); }
    public String getIsbn() { return tfISBN.getText().trim(); }
    public int getStok() { return Integer.parseInt(tfStok.getText()); }
    
    public void setData(int id, String judul, String penulis, int tahun, 
                       String kategori, String isbn, int stok) {
        tfJudul.setText(judul);
        tfPenulis.setText(penulis);
        tfTahun.setText(String.valueOf(tahun));
        tfKategori.setText(kategori);
        tfISBN.setText(isbn);
        tfStok.setText(String.valueOf(stok));
        setTitle("Edit Buku");
        btnSimpan.setText("Update");
    }
}