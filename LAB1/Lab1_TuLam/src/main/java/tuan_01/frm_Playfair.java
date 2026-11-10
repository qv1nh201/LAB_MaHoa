package tuan_01;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class frm_Playfair extends JFrame {

    private JTextArea txt_plaintext;
    private JTextField txt_key;
    private JTextArea txt_ciphertext;
    private JTextArea txt_matrix;
    private JLabel lbl_status;
    private JButton btn_encrypt;
    private JButton btn_decrypt;
    private JButton btn_matrix;
    private JButton btn_open;
    private JButton btn_save;

    public frm_Playfair() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Playfair Cipher");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lbl_title = new JLabel("MẬT MÃ PLAYFAIR", SwingConstants.CENTER);
        lbl_title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbl_title.setBounds(20, 10, 560, 30);
        add(lbl_title);

        JLabel lbl1 = new JLabel("Plaintext:");
        lbl1.setBounds(20, 45, 100, 25);
        add(lbl1);

        txt_plaintext = new JTextArea();
        txt_plaintext.setLineWrap(true);
        JScrollPane sp1 = new JScrollPane(txt_plaintext);
        sp1.setBounds(20, 70, 560, 60);
        add(sp1);

        JLabel lbl2 = new JLabel("Key:");
        lbl2.setBounds(20, 138, 40, 25);
        add(lbl2);

        txt_key = new JTextField("KEYWORD");
        txt_key.setBounds(65, 138, 120, 25);
        add(txt_key);

        btn_matrix = new JButton("Generate Key Matrix");
        btn_matrix.setBounds(195, 138, 160, 25);
        btn_matrix.addActionListener((ActionEvent evt) -> btn_matrixActionPerformed(evt));
        add(btn_matrix);

        JLabel lbl3 = new JLabel("Ciphertext:");
        lbl3.setBounds(20, 170, 100, 25);
        add(lbl3);

        txt_ciphertext = new JTextArea();
        txt_ciphertext.setLineWrap(true);
        JScrollPane sp2 = new JScrollPane(txt_ciphertext);
        sp2.setBounds(20, 195, 360, 90);
        add(sp2);

        JLabel lblM = new JLabel("Ma trận 5x5:");
        lblM.setBounds(400, 170, 100, 25);
        add(lblM);

        txt_matrix = new JTextArea();
        txt_matrix.setFont(new Font("Monospaced", Font.BOLD, 14));
        txt_matrix.setEditable(false);
        JScrollPane spM = new JScrollPane(txt_matrix);
        spM.setBounds(400, 195, 180, 90);
        add(spM);

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

    private void btn_matrixActionPerformed(ActionEvent evt) {
        try {
            txt_matrix.setText(PlayfairCipher.getKeyMatrix(txt_key.getText()));
            lbl_status.setText("Đã tạo ma trận 5x5 từ khóa.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi khóa", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_encryptActionPerformed(ActionEvent evt) {
        String plaintext = txt_plaintext.getText();
        if (plaintext.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hãy nhập Plaintext trước khi mã hóa.");
            return;
        }
        try {
            String key = txt_key.getText();
            txt_ciphertext.setText(PlayfairCipher.encrypt(plaintext, key));
            txt_matrix.setText(PlayfairCipher.getKeyMatrix(key));
            lbl_status.setText("Đã mã hóa Playfair với khóa " + key + ".");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            txt_plaintext.setText(PlayfairCipher.decrypt(ciphertext, key));
            txt_matrix.setText(PlayfairCipher.getKeyMatrix(key));
            lbl_status.setText("Đã giải mã Playfair với khóa " + key + ".");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btn_openActionPerformed(ActionEvent evt) {
        String path = FileTextIO.openText(this, txt_ciphertext);
        if (path != null) lbl_status.setText("Đã mở tệp: " + path);
    }

    private void btn_saveActionPerformed(ActionEvent evt) {
        String path = FileTextIO.saveText(this, txt_ciphertext, "playfair_ciphertext.txt");
        if (path != null) lbl_status.setText("Đã lưu tệp: " + path);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new frm_Playfair().setVisible(true));
    }
}