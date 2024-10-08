package AdaYildirim.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VeritabaniService {

    private static final String URL = "jdbc:mysql://localhost:3306/barkoddukkan";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection baglantiKur() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void parcaEkle(String isim, String marka, double fiyat, String barkod, int stokMiktari) throws Exception {
        Connection conn = baglantiKur();
        String query = "INSERT INTO parcalar (isim, marka, fiyat, barkod, stokMiktari) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, isim);
        pstmt.setString(2, marka);
        pstmt.setDouble(3, fiyat);
        pstmt.setString(4, barkod);
        pstmt.setInt(5, stokMiktari);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    // Diğer veritabanı işlemleri (güncelleme, silme, listeleme) buraya eklenebilir
}