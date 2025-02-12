package com.example.vocabulary_study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/VocabularyApp?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "123456a@";

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseConnection.class) { 
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                        System.out.println("Ket noi thanh cong");
                    } catch (SQLException e) {
                        System.err.println("khong ket noi thanh cong: " + e.getMessage());
                        e.printStackTrace(); 
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Da dong ket noi");
            } catch (SQLException e) {
                System.err.println("Loi khi dong ket noi: " + e.getMessage());
                e.printStackTrace();
            } finally {
                connection = null; 
            }
        }
    }
}
