package view.worker.buku;

import controller.BukuController;
import view.tablemodel.BukuTableModel;
import javax.swing.SwingWorker;
import java.util.List;
import model.Buku;

public class LoadBukuWorker extends SwingWorker<List<Buku>, Void> {
    private BukuController controller;
    private BukuTableModel tableModel;
    
    public LoadBukuWorker(BukuController controller, BukuTableModel tableModel) {
        this.controller = controller;
        this.tableModel = tableModel;
    }
    
    @Override
    protected List<Buku> doInBackground() throws Exception {
        Thread.sleep(300); // Simulasi loading
        return controller.getAllBuku();
    }
    
    @Override
    protected void done() {
        try {
            List<Buku> result = get();
            tableModel.setData(result);
            System.out.println("✅ Loaded " + result.size() + " books");
        } catch (Exception e) {
            System.err.println("❌ Error loading books: " + e.getMessage());
        }
    }
}