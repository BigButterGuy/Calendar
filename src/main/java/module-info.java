module design.voight {
    requires javafx.controls;
    requires javafx.fxml;

    opens design.voight to javafx.fxml;
    exports design.voight;
    exports design.voight.Exceptions;
    opens design.voight.Exceptions to javafx.fxml;
}