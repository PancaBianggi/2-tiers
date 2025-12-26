package view;

import controller.BukuController;
import view.tablemodel.BukuTableModel;
import javax.swing.*;
import java.awt.*;

public class BukuFrame extends JFrame {

    private final BukuController controller = new BukuController();
    private final BukuTableModel tableModel = new BukuTableModel();
    private final JTable table = new JTable(tableModel);

    public BukuFrame() {
        setTitle("Manajemen Buku");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnTambah = new JButton("Tambah");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        JButton btnRefresh = new JButton("Refresh");

        JPanel top = new JPanel();
        top.add(btnTambah);
        top.add(btnEdit);
        top.add(btnHapus);
        top.add(btnRefresh);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnRefresh.addActionListener(e -> loadData());
        btnTambah.addActionListener(e -> tambah());
        btnEdit.addActionListener(e -> edit());
        btnHapus.addActionListener(e -> hapus());

        loadData();
    }

    private void loadData() {
        tableModel.setData(controller.getAllBuku());
    }

    private void tambah() {
        BukuDialog d = new BukuDialog(this, "Tambah Buku");
        d.setVisible(true);

        if (d.isConfirmed()) {
            if (controller.tambahBuku(
                    d.getJudul(), d.getPenulis(), d.getTahun(),
                    d.getKategori(), d.getIsbn(), d.getStok())) {
                loadData();
            }
        }
    }

    private void edit() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) return;

        int modelRow = table.convertRowIndexToModel(viewRow);
        var buku = tableModel.getBukuAt(modelRow);

        BukuDialog d = new BukuDialog(this, "Edit Buku");
        d.setData(
                buku.getId(), buku.getJudul(), buku.getPenulis(),
                buku.getTahunTerbit(), buku.getKategori(),
                buku.getIsbn(), buku.getStok()
        );
        d.setVisible(true);

        if (d.isConfirmed()) {
            controller.updateBuku(
                    buku.getId(), d.getJudul(), d.getPenulis(),
                    d.getTahun(), d.getKategori(), d.getIsbn(), d.getStok()
            );
            loadData();
        }
    }

    private void hapus() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) return;

        int modelRow = table.convertRowIndexToModel(viewRow);
        var buku = tableModel.getBukuAt(modelRow);

        if (JOptionPane.showConfirmDialog(this,
                "Hapus " + buku.getJudul() + "?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            controller.deleteBuku(buku.getId());
            loadData();
        }
    }
}
