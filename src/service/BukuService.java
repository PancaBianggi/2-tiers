package service;

import model.Buku;
import java.util.List;

public interface BukuService {
    boolean createBuku(Buku buku);
    List<Buku> getAllBuku();
    Buku getBukuById(int id);
    boolean updateBuku(Buku buku);
    boolean deleteBuku(int id);
    List<Buku> searchBuku(String keyword);
}