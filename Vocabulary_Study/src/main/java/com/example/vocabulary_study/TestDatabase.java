package com.example.vocabulary_study;

import java.sql.Connection;

public class TestDatabase {
     public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        DatabaseConnection.closeConnection();
    }
}
