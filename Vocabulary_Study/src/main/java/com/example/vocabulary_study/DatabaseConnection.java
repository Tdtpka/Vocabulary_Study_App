package com.example.vocabulary_study;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.proerties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
   if (connection == null) {
            synchronized (DatabaseConnection.class) {
                if (connection == null) {
                    try {
                        Properties props = new Properties();
                        FileInputStream fis = new FileInputStream("Vocabulary_Study_App/Vocabulary_Study/src/main/java/com/example/Vocabulary_study/config.properties");
                        props.load(fis);

                        String dbUrl = props.getProperty("DB_URL");
                        String user = props.getProperty("USER");
                        String password = props.getProperty("PASSWORD");

                        connection = DriverManager.getConnection(dbUrl, user, password);
                        System.out.println("Ket noi thanh cong");
                    } catch (IOException | SQLException e) {
                        System.err.println("Khong ket noi thanh cong: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
}

// public static void closeConnection() {
//         if (connection != null) {
//             try {
//                 connection.close();
//                 System.out.println("Da dong ket noi");
//             } catch (SQLException e) {
//                 System.err.println("Loi khi dong ket noi: " + e.getMessage());
//                 e.printStackTrace();
//             } finally {
//                 connection = null;
//             }
//         }
//     }
// }

