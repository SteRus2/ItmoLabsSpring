module us.obviously.itmo.prog.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires us.obviously.itmo.prog.common;
    requires us.obviously.itmo.prog.client;
    requires java.desktop;

    opens us.obviously.itmo.prog.gui to javafx.fxml;
    opens us.obviously.itmo.prog.gui.controllers to javafx.fxml;
    exports us.obviously.itmo.prog.gui;
    opens us.obviously.itmo.prog.gui.tools.controllers to javafx.fxml;
}