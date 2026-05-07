module com.forensys {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    opens com.forensys.ui.controller to javafx.fxml;
    opens com.forensys.core.setting to com.fasterxml.jackson.databind;
    opens com.forensys.core.filestructure to com.fasterxml.jackson.databind;
    opens com.forensys.core.filestructure.concrete to com.fasterxml.jackson.databind;
    opens com.forensys.core.chat to com.fasterxml.jackson.databind;
    opens com.forensys.core.chat.element to com.fasterxml.jackson.databind;
    exports com.forensys;
}
