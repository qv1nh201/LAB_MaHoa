package tuan_01;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class frm_AutokeyCipher extends JFrame {

    private JTextArea txt_plaintext;
    private JTextField txt_key;
    private JTextArea txt_ciphertext;
    private JLabel lbl_status;
    private JButton btn_encrypt;
    private JButton btn_decrypt;
    private JButton btn_open;
    private JButton btn_save;

    public frm_AutokeyCipher() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Autokey Cipher");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lbl_title = new JLabel("MẬT MÃ TỰ ĐỘNG KHOÁ (AUTOKEY)", SwingConstants.CENTER);
        lbl_title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbl_title.setBounds(20, 10, 560, 30);
        add(lbl_title);

        JLabel lbl1 = new JLabel("Plaintext:");
        lbl1.setBounds(20, 50, 100, 25);
        add(lbl1);

        txt_plaintext = new JTextArea();
        txt_plaintext.setLineWrap(true);
        JScrollPane sp1 = new JScrollPane(txt_plaintext);
        sp1.setBounds(20, 75, 560, 70);
        add(sp1);

        JLabel lbl2 = new JLabel("Key mồi:");
        lbl2.setBounds(20, 155, 60, 25);
        add(lbl2);

        txt_key = new JTextField("KEY");
        txt_key.setBounds(85, 155, 100, 25);
        add(txt_key);

        JLabel lbl_hint = new JLabel("Chuỗi A-Z, ví dụ KEY");
        lbl_hint.setBounds(195, 155, 200, 25);
        add(lbl_hint);

        JLabel lbl3 = new JLabel("Ciphertext:");
        lbl3.setBounds(20, 190, 100, 25);
        add(lbl3);

        txt_ciphertext = new JTextArea();
        txt_ciphertext.setLineWrap(true);
        JScrollPane sp2 = new JScrollPane(txt_ciphertext);
        sp2.setBounds(20, 215, 560, 70);
        add(sp2);

        btn_encrypt = new JButton("Encrypt");
        btn_encrypt.setBounds(20, 300, 120, 30);
        btn_encrypt.addActionListener((ActionEvent evt) -> btn_encryptActionPerformed(evt));
        add(btn_encrypt);

        btn_decrypt = new JButton("Decrypt");
        btn_decrypt.setBounds(150, 300, 120, 30);
        btn_decrypt.addActionListener((ActionEvent evt) -> btn_decryptActionPerformed(evt));
        add(btn_decrypt);

        btn_open = new JButton("Open Ciphertext File");
        btn_open.setBounds(280, 300, 150, 30);
        btn_open.addActionListener((ActionEvent evt) -> btn_openActionPerformed(evt));
        add(btn_open);

        btn_save = new JButton("Save Ciphertext File");
        btn_save.setBounds(440, 300, 140, 30);
        btn_save.addActionListener((ActionEvent evt) -> btn_saveActionPerformed(evt));
        add(btn_save);

        lbl_status = new JLabel("Sẵn sàng.");
        lbl_status.setBounds(20, 340, 560, 25);
        add(lbl_status);

        setSize(615, 420);
    }

    private void btn_encryptActionPerformed(ActionEvent evt) {
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String key = txt_key.getText();
            txt_ciphertext.setText(AutokeyCipher.encrypt(plaintext, key));
            lbl_status.setText("Đã mã hóa Autokey với khóa mồi " + key + ".");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi khóa", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_decryptActionPerformed(ActionEvent evt) {
        String ciphertext = txt_ciphertext.getText();
        if (ciphertext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Ciphertext trước khi giải mã.");
            return;
        }
        try {
            String key = txt_key.getText();
            txt_plaintext.setText(AutokeyCipher.decrypt(ciphertext, key));
            lbl_status.setText("Đã giải mã Autokey với khóa mồi " + key + ".");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi khóa", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_openActionPerformed(ActionEvent evt) {
        String path = FileTextIO.openText(this, txt_ciphertext);
        if (path != null) lbl_status.setText("Đã mở tệp: " + path);
    }

    private void btn_saveActionPerformed(ActionEvent evt) {
        String path = FileTextIO.saveText(this, txt_ciphertext, "autokey_ciphertext.txt");
        if (path != null) lbl_status.setText("Đã lưu tệp: " + path);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new frm_AutokeyCipher().setVisible(true));
    }
}