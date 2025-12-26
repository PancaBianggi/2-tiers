package view.worker.buku;

import controller.BukuController;
import javax.swing.SwingWorker;
import javax.swing.JOptionPane;

public class DeleteBukuWorker extends SwingWorker<Boolean, Void> {
    private BukuController controller;
    private int id;
    
    public DeleteBukuWorker(BukuController controller, int id) {
        this.controller = controller;
        this.id = id;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
        Thread.sleep(200);
        return controller.deleteBuku(id);
    }
    
    @Override
    protected void done() {
        try {
            boolean success = get();
            if (success) {
                JOptionPane.showMessageDialog(null, 
                    "Buku berhasil dihapus!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Gagal menghapus buku", 
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