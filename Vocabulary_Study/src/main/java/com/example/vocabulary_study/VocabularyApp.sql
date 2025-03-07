CREATE DATABASE VocabularyApp;
USE VocabularyApp;

CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    security_question_1 VARCHAR(255) NOT NULL,
    security_answer_1 VARCHAR(255) NOT NULL,
    security_question_2 VARCHAR(255) NOT NULL,
    security_answer_2 VARCHAR(255) NOT NULL,
    security_question_3 VARCHAR(255) NOT NULL,
    security_answer_3 VARCHAR(255) NOT NULL
);

CREATE TABLE Vocabulary (
    vocab_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    topic ENUM('Giáo dục', 'Y tế', 'Giao thông', 'Kinh tế', 'Môi trường') NOT NULL,
    word VARCHAR(100) NOT NULL,
    word_type VARCHAR(50) NOT NULL,
    meaning TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE QuizResults (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    known_words_count INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE DefaultVocabulary (
    vocab_id INT AUTO_INCREMENT PRIMARY KEY,
    topic ENUM('Giáo dục', 'Y tế', 'Giao thông', 'Kinh tế', 'Môi trường') NOT NULL,
    word VARCHAR(100) NOT NULL,
    word_type VARCHAR(50) NOT NULL,
    meaning TEXT NOT NULL
);

INSERT INTO DefaultVocabulary (topic, word, word_type, meaning) VALUES
    ('Giáo dục', 'school', 'noun', 'trường học'),
    ('Giáo dục', 'teacher', 'noun', 'giáo viên'),
    ('Giáo dục', 'student', 'noun', 'học sinh'),
    ('Giáo dục', 'exam', 'noun', 'kỳ thi'),
    ('Giáo dục', 'homework', 'noun', 'bài tập về nhà'),
    ('Giáo dục', 'subject', 'noun', 'môn học'),
    ('Giáo dục', 'university', 'noun', 'đại học'),
    ('Giáo dục', 'lesson', 'noun', 'bài học'),
    ('Giáo dục', 'grade', 'noun', 'điểm số'),
    ('Giáo dục', 'notebook', 'noun', 'vở ghi'),
    ('Y tế', 'hospital', 'noun', 'bệnh viện'),
    ('Y tế', 'doctor', 'noun', 'bác sĩ'),
    ('Y tế', 'nurse', 'noun', 'y tá'),
    ('Y tế', 'medicine', 'noun', 'thuốc'),
    ('Y tế', 'patient', 'noun', 'bệnh nhân'),
    ('Y tế', 'surgery', 'noun', 'phẫu thuật'),
    ('Y tế', 'treatment', 'noun', 'điều trị'),
    ('Y tế', 'diagnosis', 'noun', 'chẩn đoán'),
    ('Y tế', 'vaccine', 'noun', 'vắc-xin'),
    ('Y tế', 'ambulance', 'noun', 'xe cứu thương'),
    ('Giao thông', 'car', 'noun', 'xe hơi'),
    ('Giao thông', 'bus', 'noun', 'xe buýt'),
    ('Giao thông', 'train', 'noun', 'tàu hỏa'),
    ('Giao thông', 'bike', 'noun', 'xe đạp'),
    ('Giao thông', 'traffic', 'noun', 'giao thông'),
    ('Giao thông', 'road', 'noun', 'con đường'),
    ('Giao thông', 'highway', 'noun', 'đường cao tốc'),
    ('Giao thông', 'ticket', 'noun', 'vé'),
    ('Giao thông', 'station', 'noun', 'nhà ga'),
    ('Giao thông', 'helmet', 'noun', 'mũ bảo hiểm'),
    ('Kinh tế', 'market', 'noun', 'chợ'),
    ('Kinh tế', 'money', 'noun', 'tiền'),
    ('Kinh tế', 'business', 'noun', 'kinh doanh'),
    ('Kinh tế', 'investment', 'noun', 'đầu tư'),
    ('Kinh tế', 'profit', 'noun', 'lợi nhuận'),
    ('Kinh tế', 'trade', 'noun', 'thương mại'),
    ('Kinh tế', 'inflation', 'noun', 'lạm phát'),
    ('Kinh tế', 'bank', 'noun', 'ngân hàng'),
    ('Kinh tế', 'salary', 'noun', 'lương'),
    ('Kinh tế', 'tax', 'noun', 'thuế'),
    ('Môi trường', 'pollution', 'noun', 'ô nhiễm'),
    ('Môi trường', 'climate', 'noun', 'khí hậu'),
    ('Môi trường', 'recycle', 'verb', 'tái chế'),
    ('Môi trường', 'energy', 'noun', 'năng lượng'),
    ('Môi trường', 'forest', 'noun', 'rừng'),
    ('Môi trường', 'waste', 'noun', 'rác thải'),
    ('Môi trường', 'conservation', 'noun', 'bảo tồn'),
    ('Môi trường', 'biodiversity', 'noun', 'đa dạng sinh học'),
    ('Môi trường', 'ecology', 'noun', 'sinh thái học'),
    ('Môi trường', 'sustainability', 'noun', 'bền vững');

DELIMITER //
CREATE TRIGGER CopyDefaultVocabulary AFTER INSERT ON Users
FOR EACH ROW
BEGIN
    INSERT INTO Vocabulary (user_id, topic, word, word_type, meaning)
    SELECT NEW.user_id, topic, word, word_type, meaning FROM DefaultVocabulary;
END //
DELIMITER ;

CREATE TABLE deleted_words (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    word_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (word_id) REFERENCES defaultvocabulary(vocab_id) ON DELETE CASCADE
);

DESC defaultvocabulary;

SELECT * FROM defaultvocabulary;

ALTER TABLE DefaultVocabulary CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE Vocabulary CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE Users CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE QuizResults CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
