package com.example.vocabulary_study.Models;

import java.sql.*;
import java.util.*;

public class QuizService {
    private static final String URL = "jdbc:mysql://localhost:3306/VocabularyApp?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456a@";

    public static class Question {
        public String word;
        public String correctAnswer;
        public List<String> options;

        public Question(String word, String correctAnswer, List<String> options) {
            this.word = word;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }
    }

    public List<Question> getQuizQuestions(int userId) {
        List<Question> questions = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = """
                WITH RandomWords AS (
                    SELECT vocab_id, word, meaning FROM Vocabulary WHERE user_id = ? 
                    UNION ALL 
                    SELECT vocab_id, word, meaning FROM DefaultVocabulary 
                    ORDER BY RAND() 
                    LIMIT 10
                ),
                WrongAnswers AS (
                    SELECT DISTINCT meaning FROM (
                        SELECT meaning FROM Vocabulary
                        UNION ALL
                        SELECT meaning FROM DefaultVocabulary
                        ORDER BY RAND()
                    ) AS temp
                    WHERE meaning NOT IN (SELECT meaning FROM RandomWords)
                    LIMIT 20
                )
                SELECT rw.word, rw.meaning AS correct_answer, wa1.meaning AS wrong_answer1, wa2.meaning AS wrong_answer2
                FROM RandomWords rw
                JOIN (SELECT meaning FROM WrongAnswers ORDER BY RAND() LIMIT 10) wa1
                JOIN (SELECT meaning FROM WrongAnswers ORDER BY RAND() LIMIT 10) wa2
                ON wa1.meaning <> wa2.meaning
                ORDER BY RAND()
                LIMIT 10;
            """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String word = rs.getString("word");
                String correctAnswer = rs.getString("correct_answer");
                String wrongAnswer1 = rs.getString("wrong_answer1");
                String wrongAnswer2 = rs.getString("wrong_answer2");

                // Tạo danh sách đáp án
                List<String> options = new ArrayList<>();
                options.add(correctAnswer);
                options.add(wrongAnswer1);
                options.add(wrongAnswer2);
                Collections.shuffle(options); // Xáo trộn thứ tự đáp án
                System.out.println("Câu hỏi: " + word + " - Đáp án sau khi xáo trộn: " + options);
                

                questions.add(new Question(word, correctAnswer, options));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
