package com.mycompany.investorcare;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.sql.*;

public class StockService {
    // Thông số kết nối SQL Server
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=INVESTORCARE;encrypt=true;trustServerCertificate=true;";
    private String user = "sa"; 
    private String pass = "12345"; 

    public void updatePrice(String symbol) {
        try {
            // 1. Lấy dữ liệu từ Yahoo
            Stock stock = YahooFinance.get(symbol);
            double currentPrice = stock.getQuote().getPrice().doubleValue();
            
            // 2. Kết nối DB
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                
                // 3. Tìm ASSET_ID của mã chứng khoán (ví dụ 'AAPL')
                int assetId = -1;
                String findAssetSql = "SELECT ASSET_ID FROM ASSET WHERE SYMBOL = ?";
                PreparedStatement ps1 = conn.prepareStatement(findAssetSql);
                ps1.setString(1, symbol);
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) {
                    assetId = rs.getInt("ASSET_ID");
                }

                // 4. Lưu vào bảng PRICE_BAR
                if (assetId != -1) {
                    String insertSql = "INSERT INTO PRICE_BAR (ASSET_ID, TS, CLOSE, SOURCE) VALUES (?, GETDATE(), ?, 'Yahoo Finance')";
                    PreparedStatement ps2 = conn.prepareStatement(insertSql);
                    ps2.setInt(1, assetId);
                    ps2.setDouble(2, currentPrice);
                    ps2.executeUpdate();
                    System.out.println("Cập nhật thành công mã " + symbol + " giá: " + currentPrice);
                } else {
                    System.out.println("Không tìm thấy mã " + symbol + " trong bảng ASSET!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}