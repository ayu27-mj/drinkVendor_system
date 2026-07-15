module org.example.drinkVendor_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.drinkVendor_system to javafx.fxml;
    exports org.example.drinkVendor_system;
}