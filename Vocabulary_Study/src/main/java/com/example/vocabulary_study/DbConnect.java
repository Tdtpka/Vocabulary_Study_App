package com.example.vocabulary_study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/VocabularyApp?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASSWORD = "123456a@"; // Thay tên user với pass SQL của mấy đứa vô

    private static Connection connection; 

    
    public static Connection getConnection() {
        if (connection == null) {  
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("ket noi thanh cong");
            } catch (ClassNotFoundException e) {
                System.err.println("Loi khong tim thay driver");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Loi ket noi");
                e.printStackTrace();
            }
        }
        return connection;  
    }

    // Đóng kết nối khi ứng dụng tắt
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; 
                System.out.println("Da dong ket noi");
            } catch (SQLException e) {
                System.err.println("Loi khi dong ket noi");
                e.printStackTrace();
            }
        }
    }
}
