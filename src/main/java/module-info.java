module com.demo.cpsc_219_w2024_g {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.testng;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.apache.commons.io;

    opens com.demo3.cpsc_219_w2024_g3 to javafx.fxml;
    exports com.demo3.cpsc_219_w2024_g3;
    exports com.demo3.cpsc_219_w2024_g3.Tests;
    opens com.demo3.cpsc_219_w2024_g3.Tests to javafx.fxml;
}