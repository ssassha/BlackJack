module com.example.blackjack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.blackjack to javafx.fxml;
    exports com.example.blackjack;
}