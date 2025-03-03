//package com.example.vocabulary_study;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ChangeVocabulary {
//
//    // Thêm từ vựng
//    public void addVocabulary(int userId, String topic, String word, String wordType, String meaning) throws SQLException {
//        String sql = "INSERT INTO Vocabulary (user_id, topic, word, word_type, meaning) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = DbConnect.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, userId);
//            stmt.setString(2, topic);
//            stmt.setString(3, word);
//            stmt.setString(4, wordType);
//            stmt.setString(5, meaning);
//            stmt.executeUpdate();
//        }
//    }
//
//    // Cập nhật từ vựng dựa vào word (từ vựng)
//    public void updateVocabulary(String word, String newTopic, String newWordType, String newMeaning) throws SQLException {
//        String sql = "UPDATE Vocabulary SET topic = ?, word_type = ?, meaning = ? WHERE word = ?";
//        try (Connection conn = DbConnect.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, newTopic);
//            stmt.setString(2, newWordType);
//            stmt.setString(3, newMeaning);
//            stmt.setString(4, word);
//            stmt.executeUpdate();
//        }
//    }
//
//    // Xoá từ vựng dựa vào word (từ vựng)
//    public void deleteVocabulary(String word) throws SQLException {
//        String sql = "DELETE FROM Vocabulary WHERE word = ?";
//        try (Connection conn = DbConnect.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, word);
//            stmt.executeUpdate();
//        }
//    }
//
//    // Lấy danh sách tất cả từ vựng
//    public List<Vocabulary> getAllVocabulary() throws SQLException {
//        List<Vocabulary> vocabList = new ArrayList<>();
//        String sql = "SELECT * FROM Vocabulary";
//        try (Connection conn = DbConnect.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                vocabList.add(new Vocabulary(
//                        rs.getInt("user_id"),
//                        rs.getString("topic"),
//                        rs.getString("word"),
//                        rs.getString("word_type"),
//                        rs.getString("meaning")
//                ));
//            }
//        }
//        return vocabList;
//    }
//}
