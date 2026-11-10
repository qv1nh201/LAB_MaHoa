package tuan_01;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class frm_TongHop extends JFrame {

    private JComboBox<String> cbo_algorithm;
    private JTextArea txt_plaintext;
    private JTextField txt_key;
    private JTextArea txt_ciphertext;
    private JLabel lbl_status;
    private JButton btn_encrypt, btn_decrypt, btn_config_db, btn_view_history;

    public frm_TongHop() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Form Tổng Hợp 6 Thuật Toán");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lbl_title = new JLabel("FORM MÃ HOÁ TỔNG HỢP & LƯU SQL SERVER", SwingConstants.CENTER);
        lbl_title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbl_title.setBounds(20, 10, 560, 30);
        add(lbl_title);

        JLabel lbl0 = new JLabel("Chọn thuật toán:");
        lbl0.setBounds(20, 50, 110, 25);
        add(lbl0);

        cbo_algorithm = new JComboBox<>(CipherTongHop.ALGORITHMS);
        cbo_algorithm.setBounds(130, 50, 160, 25);
        cbo_algorithm.addActionListener((ActionEvent evt) -> {
            String algo = (String) cbo_algorithm.getSelectedItem();
            txt_key.setText(CipherTongHop.sampleKey(algo));
        });
        add(cbo_algorithm);

        btn_config_db = new JButton("Kết nối SQL");
        btn_config_db.setBounds(310, 50, 120, 25);
        btn_config_db.addActionListener((ActionEvent evt) -> new frm_KetNoiSQL().setVisible(true));
        add(btn_config_db);

        btn_view_history = new JButton("Xem Lịch Sử");
        btn_view_history.setBounds(440, 50, 140, 25);
        btn_view_history.addActionListener((ActionEvent evt) -> new frm_LichSu().setVisible(true));
        add(btn_view_history);

        JLabel lbl1 = new JLabel("Plaintext:");
        lbl1.setBounds(20, 85, 100, 25);
        add(lbl1);

        txt_plaintext = new JTextArea();
        txt_plaintext.setLineWrap(true);
        JScrollPane sp1 = new JScrollPane(txt_plaintext);
        sp1.setBounds(20, 110, 560, 60);
        add(sp1);

        JLabel lbl2 = new JLabel("Key:");
        lbl2.setBounds(20, 180, 40, 25);
        add(lbl2);

        txt_key = new JTextField("3");
        txt_key.setBounds(65, 180, 120, 25);
        add(txt_key);

        JLabel lbl3 = new JLabel("Ciphertext:");
        lbl3.setBounds(20, 215, 100, 25);
        add(lbl3);

        txt_ciphertext = new JTextArea();
        txt_ciphertext.setLineWrap(true);
        JScrollPane sp2 = new JScrollPane(txt_ciphertext);
        sp2.setBounds(20, 240, 560, 60);
        add(sp2);

        btn_encrypt = new JButton("Encrypt");
        btn_encrypt.setBounds(20, 315, 120, 30);
        btn_encrypt.addActionListener((ActionEvent evt) -> btn_encryptActionPerformed(evt));
        add(btn_encrypt);

        btn_decrypt = new JButton("Decrypt");
        btn_decrypt.setBounds(150, 315, 120, 30);
        btn_decrypt.addActionListener((ActionEvent evt) -> btn_decryptActionPerformed(evt));
        add(btn_decrypt);

        lbl_status = new JLabel("Sẵn sàng.");
        lbl_status.setBounds(20, 355, 560, 25);
        add(lbl_status);

        setSize(615, 430);
    }

    private void btn_encryptActionPerformed(ActionEvent evt) {
        String algo = (String) cbo_algorithm.getSelectedItem();
        String input = txt_plaintext.getText();
        try {
            String result = CipherTongHop.encrypt(algo, input, txt_key.getText());
            txt_ciphertext.setText(result);
            String saved = HistoryDao.tryInsert(algo, "ENCRYPT", input, result);
            lbl_status.setText("Đã mã hóa " + algo + ". " + saved);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_decryptActionPerformed(ActionEvent evt) {
        String algo = (String) cbo_algorithm.getSelectedItem();
        String input = txt_ciphertext.getText();
        try {
            String result = CipherTongHop.decrypt(algo, input, txt_key.getText());
            txt_plaintext.setText(result);
            String saved = HistoryDao.tryInsert(algo, "DECRYPT", input, result);
            lbl_status.setText("Đã giải mã " + algo + ". " + saved);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new frm_TongHop().setVisible(true));
    }
}