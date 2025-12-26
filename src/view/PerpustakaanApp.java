package view;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

public class PerpustakaanApp {

    public static void main(String[] args) {

        // Set Look and Feel (FlatLaf)
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("❌ Failed to set Look and Feel: " + e.getMessage());
        }

        // Jalankan UI di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                BukuFrame frame = new BukuFrame();
                frame.setVisible(true);

                // Pesan selamat datang
                JOptionPane.showMessageDialog(frame,
                        "🎉 SELAMAT DATANG\n\n" +
                        "Sistem Manajemen Perpustakaan\n" +
                        "Arsitektur 2-Tier (Java Swing + MySQL)\n\n" +
                        "Database : perpustakaan\n" +
                        "Server   : Laragon MySQL\n\n" +
                        "© 2025 - D3TI Politeknik Negeri Indramayu",
                        "Welcome",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Gagal menjalankan aplikasi:\n" + e.getMessage(),
                        "Fatal Error",
                        JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }
        });
    }
}
