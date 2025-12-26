package service;

import dao.BukuDao;
import dao.mysql.BukuDaoMySql;
import model.Buku;
import java.util.List;

public class BukuServiceDefault implements BukuService {
    private BukuDao bukuDao;
    
    public BukuServiceDefault() {
        this.bukuDao = new BukuDaoMySql();
    }
    
    @Override
    public boolean createBuku(Buku buku) {
        // Validasi
        if (buku.getJudul() == null || buku.getJudul().trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong");
        }
        if (buku.getPenulis() == null || buku.getPenulis().trim().isEmpty()) {
            throw new IllegalArgumentException("Penulis tidak boleh kosong");
        }
        if (buku.getTahunTerbit() < 1900 || buku.getTahunTerbit() > 2100) {
            throw new IllegalArgumentException("Tahun terbit tidak valid");
        }
        
        return bukuDao.create(buku);
    }
    
    @Override
    public List<Buku> getAllBuku() {
        return bukuDao.readAll();
    }
    
    @Override
    public Buku getBukuById(int id) {
        return bukuDao.readById(id);
    }
    
    @Override
    public boolean updateBuku(Buku buku) {
        if (buku.getId() <= 0) {
            throw new IllegalArgumentException("ID buku tidak valid");
        }
        return bukuDao.update(buku);
    }
    
    @Override
    public boolean deleteBuku(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID buku tidak valid");
        }
        return bukuDao.delete(id);
    }
    
    @Override
    public List<Buku> searchBuku(String keyword) {
        return bukuDao.search(keyword);
    }
}