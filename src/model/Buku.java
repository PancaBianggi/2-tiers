package model;

import java.time.LocalDateTime;

public class Buku {
    private int id;
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private String kategori;
    private String isbn;
    private int stok;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Buku() {}
    
    public Buku(String judul, String penulis, int tahunTerbit, 
                String kategori, String isbn, int stok) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.isbn = isbn;
        this.stok = stok;
    }
    
    // Getters
    public int getId() { return id; }
    public String getJudul() { return judul; }
    public String getPenulis() { return penulis; }
    public int getTahunTerbit() { return tahunTerbit; }
    public String getKategori() { return kategori; }
    public String getIsbn() { return isbn; }
    public int getStok() { return stok; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setJudul(String judul) { this.judul = judul; }
    public void setPenulis(String penulis) { this.penulis = penulis; }
    public void setTahunTerbit(int tahunTerbit) { this.tahunTerbit = tahunTerbit; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setStok(int stok) { this.stok = stok; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @Override
    public String toString() {
        return judul + " (" + penulis + ", " + tahunTerbit + ")";
    }
}