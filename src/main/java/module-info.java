module com.example.estoquejava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.estoquejava to javafx.fxml;
    exports com.example.estoquejava;
}