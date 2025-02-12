module com.example.vocabulary_study {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires fontawesomefx;

    exports com.example.vocabulary_study.Controllers;
    opens com.example.vocabulary_study.Controllers to javafx.fxml;
    exports com.example.vocabulary_study.Views;
    opens com.example.vocabulary_study.Views to javafx.fxml;

  
    
}