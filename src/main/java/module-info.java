module com.demo3.cpsc_219_w2024_g3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.demo3.cpsc_219_w2024_g3 to javafx.fxml;
    exports com.demo3.cpsc_219_w2024_g3;
}