# BÁO CÁO BÀI THỰC HÀNH LAB 1: MÃ HÓA ĐỐI XỨNG CỔ ĐIỂN

* **Môn học:** An toàn và bảo mật dữ liệu
* **Họ và tên sinh viên:** Trần Hồ Quang Vinh
* **Mã số sinh viên:** 1150080082
* **Lớp package:** `tuan_01`
* **Project Maven:** `LAB1_MaHoaCoDien`

---

## 📌 1. NỘI DUNG THỰC HÀNH

Dự án triển khai các thuật toán mã hóa đối xứng cổ điển và các kỹ thuật phân tích tấn công mật mã trên giao diện Java Swing GUI (Maven Project), kết nối cơ sở dữ liệu SQL Server để lưu trữ lịch sử thao tác.

### 1.1. Sáu thuật toán cổ điển cơ bản
1. **Mật mã Caesar (Caesar Cipher):** Mã hóa dịch chuyển với khóa $k \in \mathbb{Z}$.
2. **Mật mã Vigenère (Vigenère Cipher):** Mã hóa thay thế đa bảng với chuỗi khóa chữ cái.
3. **Mật mã Hàng rào (Rail Fence Cipher):** Mã hóa hoán vị dạng ziczac theo số hàng $N$.
4. **Mật mã Playfair (Playfair Cipher):** Mã hóa thay thế theo cặp chữ (digram) dựa trên ma trận $5 \times 5$.
5. **Mật mã Hoán vị cột (Transposition Cipher):** Mã hóa hoán vị vị trí các cột theo mảng khóa.
6. **Mật mã Tự động khóa (Autokey Cipher):** Mã hóa đa bảng mở rộng khóa bằng chính bản rõ.

### 1.2. Các chức năng nâng cao & Phân tích tấn công
* **Form Tổng hợp & Lưu SQL Server:** Điều hướng 6 thuật toán, tự động lưu nhật ký thao tác `(ENCRYPT/DECRYPT)` vào cơ sở dữ liệu SQL Server.
* **Lịch sử thao tác (History DAO):** Xem lịch sử 50 thao tác gần nhất bằng `JTable`.
* **Phân tích Tấn công Mật mã (Cryptanalysis Demos):**
  * *Brute-force Attack:* Vét cạn 26 khóa Caesar.
  * *Frequency Analysis:* Thống kê tần suất ký tự A-Z tìm khóa.
  * *Known-Plaintext Attack:* Thám mã suy ra chuỗi khóa Vigenère khi biết trước cặp (Plaintext, Ciphertext).

---

## 📂 2. CẤU TRÚC THƯ MỤC DỰ ÁN

```text
LAB1_MaHoaCoDien/
├── pom.xml                                  # File cấu hình Maven & Dependencies (mssql-jdbc)
└── src/
    └── main/
        └── java/
            └── tuan_01/                     # Source code Java Swing & Cryptography logic
                ├── FileTextIO.java          # Đọc/Lưu file .txt UTF-8
                ├── CaesarCipher.java        # Thuật toán Caesar
                ├── VigenereCipher.java      # Thuật toán Vigenère
                ├── RailFenceCipher.java     # Thuật toán Rail Fence
                ├── PlayfairCipher.java      # Thuật toán Playfair
                ├── TranspositionCipher.java # Thuật toán Transposition
                ├── AutokeyCipher.java       # Thuật toán Autokey
                ├── CipherTongHop.java       # Bộ điều hướng xử lý mã hóa tổng hợp
                ├── DbConfig.java            # Cấu hình kết nối JDBC SQL Server
                ├── HistoryDao.java          # Xử lý truy vấn SQL (INSERT / SELECT history)
                ├── AttackDemos.java         # Thuật toán Brute-force, Frequency, Known-Plaintext
                ├── frm_Caesar.java          # GUI Mật mã Caesar
                ├── frm_Vigenere.java        # GUI Mật mã Vigenère
                ├── frm_RailFence.java       # GUI Mật mã Rail Fence
                ├── frm_Playfair.java        # GUI Mật mã Playfair
                ├── frm_Transposition.java   # GUI Mật mã Hoán vị cột
                ├── frm_Autokey.java         # GUI Mật mã Autokey
                ├── frm_KetNoiSQL.java       # GUI Cấu hình & Test kết nối SQL Server
                ├── frm_LichSu.java          # GUI Xem lịch sử mã hóa/giải mã từ CSDL
                ├── frm_TongHop.java         # GUI Mã hóa tổng hợp & tự động lưu CSDL
                ├── frm_TanCong.java         # GUI Phân tích & Thử nghiệm tấn công mật mã
                └── frm_Menu.java            # GUI Menu điều khiển chính của ứng dụng
```

---

## 🛠️ 3. HƯỚNG DẪN CÀI ĐẶT VÀ CHẠY DỰ ÁN

### 3.1. Yêu cầu môi trường
* **JDK:** Java 17 trở lên.
* **IDE:** Apache NetBeans (hoặc IntelliJ IDEA / Eclipse).
* **Database:** Microsoft SQL Server (đã bật giao thức TCP/IP trên port `1433` hoặc `14333`).

### 3.2. Cấu hình Cơ sở dữ liệu SQL Server
1. Mở **SQL Server Management Studio (SSMS)**.
2. Chạy lần lượt các file script trong thư mục `sql/`:
   * Chạy `01_Tao_CSDL.sql` để tạo database `LAB1_MaHoaCoDien` và bảng `dbo.history`.
   * Tạo tài khoản người dùng đăng nhập SQL: Username `lab1_user` / Password `LabUser@2026`.

### 3.3. Biên dịch và Khởi chạy ứng dụng
1. Mở dự án trong NetBeans IDE.
2. Thực hiện **Clean and Build** project (`Shift + F11`).
3. Khởi chạy ứng dụng bằng cách bấm **Run Project** (`F6`) hoặc nhấp chuột phải vào `frm_Menu.java` chọn **Run File** (`Shift + F6`).

---

## 🧪 4. BẢNG DỮ LIỆU KIỂM THỬ (TEST CASES)

| Thuật toán | Plaintext | Key | Ciphertext (Kết quả mã hóa) |
| :--- | :--- | :--- | :--- |
| **Caesar** | `HELLO` | `3` | `KHOOR` |
| **Vigenère** | `HELLO` | `KEY` | `RIJVS` |
| **Rail Fence** | `HELLO WORLD` | `3` | `HOREL OLLWD` |
| **Playfair** | `HELLOWORLD` | `KEYWORD` | `GYIZSCOKCFBU` |
| **Transposition** | `BAOMAT` | `2,3,1` | `AAOTBM` |
| **Autokey** | `HELLO` | `KEY` | `RIJSS` |