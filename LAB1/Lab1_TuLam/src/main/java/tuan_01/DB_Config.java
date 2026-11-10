package tuan_01;

import java.sql.*;

public final class DB_Config {
    private static String server, database, user;
    private static int port;
    private static char[] password;
    private DB_Config() {}

    public static synchronized void set(String s, int p, String db, String u, char[] pass) {
        if (s.isBlank() || db.isBlank() || u.isBlank()) throw new IllegalArgumentException("Trường dữ liệu trống.");
        server = s; port = p; database = db; user = u; password = pass.clone();
    }

    public static synchronized boolean isReady() {
        return server != null;
    }

    public static synchronized Connection open() throws SQLException {
        if (server == null) throw new SQLException("Chưa cấu hình SQL Server.");
        String url = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";encrypt=true;trustServerCertificate=true;loginTimeout=5";
        return DriverManager.getConnection(url, user, new String(password));
    }

    public static String test() throws SQLException {
        try (Connection con = open()) {
            DatabaseMetaData md = con.getMetaData();
            return "Kết nối thành công: " + md.getDatabaseProductName() + " " + md.getDatabaseProductVersion() + " | Database: " + con.getCatalog();
        }
    }
}