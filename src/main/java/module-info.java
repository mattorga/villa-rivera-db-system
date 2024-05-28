module com.example.villariveradbsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    exports com.example.villariveradbsystem.loginprogram;
    opens com.example.villariveradbsystem.loginprogram to javafx.fxml;
    exports com.example.villariveradbsystem.inquiryprogram;
    opens com.example.villariveradbsystem.inquiryprogram to javafx.fxml;
    opens com.example.villariveradbsystem.databasegui to javafx.fxml;
    exports com.example.villariveradbsystem.databasegui;
    exports com.example.villariveradbsystem;
    opens com.example.villariveradbsystem to javafx.fxml;
}