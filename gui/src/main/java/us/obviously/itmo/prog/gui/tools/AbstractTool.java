package us.obviously.itmo.prog.gui.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import us.obviously.itmo.prog.gui.i18n.Internalization;

import java.util.Objects;

public final class AbstractTool {
    public final Button button;
    private final String displayName;
    private final EventHandler<ActionEvent> event;
    private final static String namePrefix = "tools.";

    public AbstractTool(String displayName, EventHandler<ActionEvent> event) {
        this.displayName = displayName;
        this.event = event;
        this.button = new Button();
        this.button.setOnAction(event);
        this.button.setPrefWidth(600);
        updateText();
    }

    public void updateText() {
        button.setText(Internalization.getTranslation(namePrefix + displayName));
    }

    public String displayName() {
        return displayName;
    }

    public EventHandler<ActionEvent> event() {
        return event;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AbstractTool) obj;
        return Objects.equals(this.displayName, that.displayName) &&
                Objects.equals(this.event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName, event);
    }

    @Override
    public String toString() {
        return "AbstractTool[" +
                "displayName=" + displayName + ", " +
                "event=" + event + ']';
    }

}
