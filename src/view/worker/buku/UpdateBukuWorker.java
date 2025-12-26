package view.worker.buku;

import controller.BukuController;
import javax.swing.SwingWorker;
import javax.swing.JOptionPane;

public class UpdateBukuWorker extends SwingWorker<Boolean, Void> {
    private BukuController controller;
    private int id;
    private String[] data;
    
    public UpdateBukuWorker(BukuController controller, int id, 
                           String judul, String penulis, int tahun, 
                           String kategori, String isbn, int stok) {
        this.controller = controller;
        this.id = id;
        this.data = new String[]{judul, penulis, String.valueOf(tahun), 
                                kategori, isbn, String.valueOf(stok)};
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        Thread.sleep(200);
        return controller.updateBuku(
            id, data[0], data[1], Integer.parseInt(data[2]), 
            data[3], data[4], Integer.parseInt(data[5])
        );
    }
    
    @Override
    protected void done() {
        try {
            boolean success = get();
            if (success) {
                JOptionPane.showMessageDialog(null, 
                    "Buku berhasil diperbarui!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Gagal memperbarui buku", 
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