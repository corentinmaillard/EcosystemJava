module com.example.eco {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eco to javafx.fxml;
    exports com.example.eco;
    exports com.example.eco.MODEL;
    opens com.example.eco.MODEL to javafx.fxml;
}