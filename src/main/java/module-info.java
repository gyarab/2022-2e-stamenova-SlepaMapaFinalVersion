module com.example.rp_slepamapa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.rp_slepamapa to javafx.fxml;
    exports com.example.rp_slepamapa;
}