package tuan_01;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class frm_KetNoiSQL extends JFrame {

    private JTextField txt_server;
    private JTextField txt_port;
    private JTextField txt_database;
    private JTextField txt_user;
    private JPasswordField txt_password;
    private JLabel lbl_status;
    private JButton btn_test;

    public frm_KetNoiSQL() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Cấu hình Kết nối SQL Server");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lbl_title = new JLabel("CẤU HÌNH KẾT NỐI SQL SERVER", SwingConstants.CENTER);
        lbl_title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl_title.setBounds(20, 10, 360, 30);
        add(lbl_title);

        addLabel("Server / IP:", 20, 50);
        txt_server = new JTextField("localhost");
        txt_server.setBounds(120, 50, 240, 25);
        add(txt_server);

        addLabel("Port:", 20, 85);
        txt_port = new JTextField("14333");
        txt_port.setBounds(120, 85, 240, 25);
        add(txt_port);

        addLabel("Database:", 20, 120);
        txt_database = new JTextField("LAB1_MaHoaCoDien");
        txt_database.setBounds(120, 120, 240, 25);
        add(txt_database);

        addLabel("Username:", 20, 155);
        txt_user = new JTextField("lab1_user");
        txt_user.setBounds(120, 155, 240, 25);
        add(txt_user);

        addLabel("Password:", 20, 190);
        txt_password = new JPasswordField("LabUser@2026");
        txt_password.setBounds(120, 190, 240, 25);
        add(txt_password);

        btn_test = new JButton("Test Connection");
        btn_test.setBounds(120, 230, 160, 30);
        btn_test.addActionListener((ActionEvent evt) -> btn_testActionPerformed(evt));
        add(btn_test);

        lbl_status = new JLabel("Chưa kết nối.", SwingConstants.CENTER);
        lbl_status.setBounds(20, 270, 360, 40);
        add(lbl_status);

        setSize(410, 360);
    }

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 90, 25);
        add(lbl);
    }

    private void btn_testActionPerformed(ActionEvent evt) {
        try {
            int port = Integer.parseInt(txt_port.getText().trim());
            DB_Config.set(txt_server.getText().trim(), port, txt_database.getText().trim(), txt_user.getText().trim(), txt_password.getPassword());
            lbl_status.setText("<html><font color='green'>" + DB_Config.test() + "</font></html>");
        } catch (Exception ex) {
            lbl_status.setText("<html><font color='red'>Lỗi: " + ex.getMessage() + "</font></html>");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new frm_KetNoiSQL().setVisible(true));
    }
}