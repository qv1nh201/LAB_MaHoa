package tuan_01;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public final class HistoryDao {
    private HistoryDao() {}

    public static String tryInsert(String algorithm, String operation, String input, String result) {
        if (!DB_Config.isReady()) return "Chưa kết nối SQL Server nên chưa lưu lịch sử.";
        String sql = "INSERT INTO dbo.history(algorithm, operation, input_text, result_text) VALUES (?, ?, ?, ?)";
        try (Connection con = DB_Config.open(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, algorithm);
            ps.setString(2, operation);
            ps.setString(3, input);
            ps.setString(4, result);
            ps.executeUpdate();
            return "Đã lưu lịch sử vào SQL Server.";
        } catch (SQLException ex) {
            return "Lỗi lưu lịch sử: " + ex.getMessage();
        }
    }

    public static DefaultTableModel emptyModel() {
        return new DefaultTableModel(new Object[]{"ID", "Thời gian", "Thuật toán", "Thao tác", "Đầu vào", "Kết quả"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
    }

    public static DefaultTableModel loadLatest(int top) throws SQLException {
        DefaultTableModel model = emptyModel();
        String sql = "SELECT TOP (?) id, created_at, algorithm, operation, input_text, result_text FROM dbo.history ORDER BY id DESC";
        try (Connection con = DB_Config.open(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, top);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{rs.getLong(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
                }
            }
        }
        return model;
    }
}