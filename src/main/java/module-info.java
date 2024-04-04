module org.example.prueba {
    requires javafx.controls;
    requires javafx.fxml;

    requires static lombok;
    requires java.sql;
    requires MaterialFX;


    opens org.example.prueba to javafx.fxml;
    exports org.example.prueba;
    exports org.example.prueba.Controller;
    opens org.example.prueba.Controller to javafx.fxml;
}