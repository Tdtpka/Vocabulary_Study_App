package com.example.vocabulary_study;

import com.example.vocabulary_study.Models.DatabaseDriver;

import java.sql.Connection;

public class TestDatabase {
    public static void main(String[] args) {
        Connection conn = DbConnect.getConnection();
        if (conn != null) {
            System.out.println("ket noi thanh cong");
            System.out.println();
           
        } else {
            System.err.println("ket noi that bai");
        }
    }
}
