package dao.mysql;

import dao.BukuDao;
import config.DatabaseConnection;
import model.Buku;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BukuDaoMySql implements BukuDao {
    
    @Override
    public boolean create(Buku buku) {
        String sql = "INSERT INTO buku (judul, penulis, tahun_terbit, kategori, isbn, stok) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, buku.getJudul());
            pstmt.setString(2, buku.getPenulis());
            pstmt.setInt(3, buku.getTahunTerbit());
            pstmt.setString(4, buku.getKategori());
            pstmt.setString(5, buku.getIsbn());
            pstmt.setInt(6, buku.getStok());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error create buku: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Buku> readAll() {
        List<Buku> bukuList = new ArrayList<>();
        String sql = "SELECT * FROM buku ORDER BY id DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                bukuList.add(mapResultSetToBuku(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error read all buku: " + e.getMessage());
        }
        
        return bukuList;
    }
    
    @Override
    public Buku readById(int id) {
        String sql = "SELECT * FROM buku WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToBuku(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error read by id: " + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public boolean update(Buku buku) {
        String sql = "UPDATE buku SET judul=?, penulis=?, tahun_terbit=?, kategori=?, isbn=?, stok=? WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, buku.getJudul());
            pstmt.setString(2, buku.getPenulis());
            pstmt.setInt(3, buku.getTahunTerbit());
            pstmt.setString(4, buku.getKategori());
            pstmt.setString(5, buku.getIsbn());
            pstmt.setInt(6, buku.getStok());
            pstmt.setInt(7, buku.getId());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error update buku: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM buku WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("❌ Error delete buku: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Buku> search(String keyword) {
        List<Buku> bukuList = new ArrayList<>();
        String sql = "SELECT * FROM buku WHERE judul LIKE ? OR penulis LIKE ? OR kategori LIKE ? OR isbn LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String likeKeyword = "%" + keyword + "%";
            pstmt.setString(1, likeKeyword);
            pstmt.setString(2, likeKeyword);
            pstmt.setString(3, likeKeyword);
            pstmt.setString(4, likeKeyword);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                bukuList.add(mapResultSetToBuku(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error search buku: " + e.getMessage());
        }
        
        return bukuList;
    }
    
    private Buku mapResultSetToBuku(ResultSet rs) throws SQLException {
        Buku buku = new Buku();
        buku.setId(rs.getInt("id"));
        buku.setJudul(rs.getString("judul"));
        buku.setPenulis(rs.getString("penulis"));
        buku.setTahunTerbit(rs.getInt("tahun_terbit"));
        buku.setKategori(rs.getString("kategori"));
        buku.setIsbn(rs.getString("isbn"));
        buku.setStok(rs.getInt("stok"));
        return buku;
    }
}