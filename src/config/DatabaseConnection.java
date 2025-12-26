package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/perpus_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Koneksi database berhasil!");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Driver MySQL tidak ditemukan: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("❌ Gagal koneksi ke database: " + e.getMessage());
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("✅ Koneksi database ditutup.");
            } catch (SQLException e) {
                System.err.println("❌ Error menutup koneksi: " + e.getMessage());
            }
        }
    }
}