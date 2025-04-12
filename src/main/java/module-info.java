module net.totaldarkness.othello {
    requires javafx.controls;
    requires javafx.fxml;

    opens net.totaldarkness.othello.viewcontroller to javafx.fxml;
    exports net.totaldarkness.othello.viewcontroller;
}