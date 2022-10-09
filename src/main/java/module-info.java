module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;

    opens com.example.entity to javafx.fxml;
    exports com.example.entity;
    opens com.example.boundary to javafx.fxml;
    exports com.example.boundary;
    opens com.example.control to javafx.fxml;
    exports com.example.control;
    exports com.example.daos;
    opens com.example.daos to javafx.fxml;
    exports com.example.main;
    opens com.example.main to javafx.fxml;
}