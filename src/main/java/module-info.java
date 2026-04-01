module com.investigacao {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.investigacao.controller to javafx.fxml;
    exports com.investigacao;
}
