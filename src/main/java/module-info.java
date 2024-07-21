module com.example.estoquejava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.estoquejava to javafx.fxml;
    exports com.example.estoquejava;
    exports com.example.estoquejava.helloJavaFX;
    opens com.example.estoquejava.helloJavaFX to javafx.fxml;
}