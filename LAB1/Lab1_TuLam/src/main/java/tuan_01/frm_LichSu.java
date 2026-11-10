package tuan_01;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class frm_LichSu extends JFrame {

    private JTable tbl_history;
    private JLabel lbl_status;
    private JButton btn_reload;

    public frm_LichSu() {
        initComponents();
        setLocationRelativeTo(null);
        loadData();
    }

    private void initComponents() {
        setTitle("Lịch sử Thao tác");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lbl_title = new JLabel("LỊCH SỬ MÃ HOÁ / GIẢI MÃ (CSDL)", SwingConstants.CENTER);
        lbl_title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbl_title.setBounds(20, 10, 740, 30);
        add(lbl_title);

        tbl_history = new JTable(HistoryDao.emptyModel());
        JScrollPane sp = new JScrollPane(tbl_history);
        sp.setBounds(20, 50, 740, 300);
        add(sp);

        btn_reload = new JButton("Tải lại lịch sử");
        btn_reload.setBounds(20, 360, 140, 30);
        btn_reload.addActionListener((ActionEvent evt) -> loadData());
        add(btn_reload);

        lbl_status = new JLabel("Đang tải dữ liệu...");
        lbl_status.setBounds(180, 360, 580, 30);
        add(lbl_status);

        setSize(795, 440);
    }

    private void loadData() {
        try {
            tbl_history.setModel(HistoryDao.loadLatest(50));
            lbl_status.setText("Đã tải " + tbl_history.getRowCount() + " dòng mới nhất từ dbo.history.");
        } catch (Exception ex) {
            lbl_status.setText("Không thể tải lịch sử: " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new frm_LichSu().setVisible(true));
    }
}