package us.obviously.itmo.prog.gui.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public record AbstractTool(String displayName, EventHandler<ActionEvent> event) {
}
