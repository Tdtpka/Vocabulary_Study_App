package com.example.vocabulary_study;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class ChangeVocabularyPage extends Application {
    private TableView<Vocabulary> table = new TableView<>();
    private ChangeVocabulary vocabularyDAO = new ChangeVocabulary();
    private ObservableList<Vocabulary> vocabList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Cột bảng
        TableColumn<Vocabulary, String> wordCol = new TableColumn<>("Word");
        wordCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getWord()));

        TableColumn<Vocabulary, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getWordType()));

        TableColumn<Vocabulary, String> topicCol = new TableColumn<>("Topic");
        topicCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTopic()));

        TableColumn<Vocabulary, String> meaningCol = new TableColumn<>("Meaning");
        meaningCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getMeaning()));

        table.getColumns().addAll(wordCol, typeCol, topicCol, meaningCol);
        loadVocabulary();

        // Form nhập liệu
        TextField wordField = new TextField();
        wordField.setPromptText("Word");

        TextField typeField = new TextField();
        typeField.setPromptText("Type");

        TextField meaningField = new TextField();
        meaningField.setPromptText("Meaning");

        ComboBox<String> topicBox = new ComboBox<>();
        topicBox.getItems().addAll("Giáo dục", "Y tế", "Giao thông", "Kinh tế", "Môi trường");
        topicBox.setPromptText("Topic");

        // Nút Thêm từ vựng
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                vocabularyDAO.addVocabulary(1, topicBox.getValue(), wordField.getText(), typeField.getText(), meaningField.getText());
                loadVocabulary();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Nút Cập nhật từ vựng
        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            Vocabulary selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    vocabularyDAO.updateVocabulary(selected.getWord(), topicBox.getValue(), typeField.getText(), meaningField.getText());
                    loadVocabulary();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Nút Xoá từ vựng
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Vocabulary selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    vocabularyDAO.deleteVocabulary(selected.getWord());
                    loadVocabulary();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        VBox vbox = new VBox(10, table, wordField, typeField, topicBox, meaningField, addButton, updateButton, deleteButton);
        vbox.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(vbox, 600, 400));
        primaryStage.setTitle("Vocabulary Manager");
        primaryStage.show();
    }

    private void loadVocabulary() {
        vocabList.clear();
        try {
            List<Vocabulary> vocabData = vocabularyDAO.getAllVocabulary();
            vocabList.addAll(vocabData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.setItems(vocabList);
    }
}
