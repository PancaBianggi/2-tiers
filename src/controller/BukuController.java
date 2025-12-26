package controller;

import service.BukuService;
import service.BukuServiceDefault;
import model.Buku;
import java.util.List;

public class BukuController {
    private BukuService bukuService;
    
    public BukuController() {
        this.bukuService = new BukuServiceDefault();
    }
    
    public boolean tambahBuku(String judul, String penulis, int tahun, 
                             String kategori, String isbn, int stok) {
        try {
            Buku buku = new Buku(judul, penulis, tahun, kategori, isbn, stok);
            return bukuService.createBuku(buku);
        } catch (Exception e) {
            System.err.println("Error tambah buku: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public List<Buku> getAllBuku() {
        return bukuService.getAllBuku();
    }
    
    public Buku getBukuById(int id) {
        return bukuService.getBukuById(id);
    }
    
    public boolean updateBuku(int id, String judul, String penulis, int tahun,
                             String kategori, String isbn, int stok) {
        try {
            Buku buku = new Buku(judul, penulis, tahun, kategori, isbn, stok);
            buku.setId(id);
            return bukuService.updateBuku(buku);
        } catch (Exception e) {
            System.err.println("Error update buku: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public boolean deleteBuku(int id) {
        return bukuService.deleteBuku(id);
    }
    
    public List<Buku> searchBuku(String keyword) {
        return bukuService.searchBuku(keyword);
    }
}