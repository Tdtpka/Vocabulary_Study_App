package com.example.vocabulary_study;

import java.sql.Connection;

public class TestDatabase {
    public static void main(String[] args) {
        Connection conn = DbConnect.getConnection();
        if (conn != null) {
            System.out.println("ket noi thanh cong");
           
        } else {
            System.err.println("ket noi that bai");
        }
    }
}
