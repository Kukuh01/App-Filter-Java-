module org.silvanus.develop.appfilterfoto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jdk.jshell;
    requires javafx.swing;

    opens org.silvanus.develop.appfilterfoto to javafx.fxml;
    exports org.silvanus.develop.appfilterfoto;
    exports org.silvanus.develop.appfilterfoto.controller;
    opens org.silvanus.develop.appfilterfoto.controller to javafx.fxml;
}