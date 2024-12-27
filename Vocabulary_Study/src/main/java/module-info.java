module com.example.vocabulary_study {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.vocabulary_study to javafx.fxml;
    exports com.example.vocabulary_study;
}