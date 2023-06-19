package us.obviously.itmo.prog.gui.notifications;

import javafx.scene.control.Alert;
import javafx.stage.Window;
import javafx.util.Duration;

public class Notifications {

    public static boolean result = false;

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        if (alertType.name().equals(Alert.AlertType.INFORMATION.name())) {
            org.controlsfx.control.Notifications.create()
                    .darkStyle()
                    .title(title)
                    .text(message).hideAfter(Duration.seconds(10))
                    .showInformation();
        } else if (alertType.name().equals(Alert.AlertType.ERROR.name())) {
            org.controlsfx.control.Notifications.create()
                    .darkStyle()
                    .title(title)
                    .text(message).hideAfter(Duration.seconds(10))
                    .showError();
        }
    }
}