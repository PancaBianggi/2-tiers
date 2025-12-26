package view.worker.buku;

import controller.BukuController;
import javax.swing.SwingWorker;
import javax.swing.JOptionPane;

public class SaveBukuWorker extends SwingWorker<Boolean, Void> {
    private BukuController controller;
    private String[] data;
    
    public SaveBukuWorker(BukuController controller, String judul, String penulis, 
                         int tahun, String kategori, String isbn, int stok) {
        this.controller = controller;
        this.data = new String[]{judul, penulis, String.valueOf(tahun), 
                                kategori, isbn, String.valueOf(stok)};
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        Thread.sleep(200);
        return controller.tambahBuku(
            data[0], data[1], Integer.parseInt(data[2]), 
            data[3], data[4], Integer.parseInt(data[5])
        );
    }
    
    @Override
    protected void done() {
        try {
            boolean success = get();
            if (success) {
                JOptionPane.showMessageDialog(null, 
                    "Buku berhasil ditambahkan!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Gagal menambahkan buku", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}