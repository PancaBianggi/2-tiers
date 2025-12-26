package view.tablemodel;

import model.Buku;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class BukuTableModel extends AbstractTableModel {
    private List<Buku> data;
    private String[] columnNames = {"ID", "Judul", "Penulis", "Tahun", "Kategori", "ISBN", "Stok"};
    
    public BukuTableModel() {
        this.data = new ArrayList<>();
    }
    
    public void setData(List<Buku> data) {
        this.data = data;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Buku buku = data.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return buku.getId();
            case 1: return buku.getJudul();
            case 2: return buku.getPenulis();
            case 3: return buku.getTahunTerbit();
            case 4: return buku.getKategori();
            case 5: return buku.getIsbn();
            case 6: return buku.getStok();
            default: return null;
        }
    }
    
    public Buku getBukuAt(int row) {
        if (row >= 0 && row < data.size()) {
            return data.get(row);
        }
        return null;
    }
}