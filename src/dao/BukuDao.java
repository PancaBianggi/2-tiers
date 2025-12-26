package dao;

import model.Buku;
import java.util.List;

public interface BukuDao {
    boolean create(Buku buku);
    List<Buku> readAll();
    Buku readById(int id);
    boolean update(Buku buku);
    boolean delete(int id);
    List<Buku> search(String keyword);
}