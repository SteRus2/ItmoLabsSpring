package us.obviously.itmo.prog.gui.tools.fields;

import javafx.scene.control.Control;

public abstract class AbstractReqField<S, T extends Control> extends AbstractField<S, T, S> {
    public AbstractReqField(String label, T control) {
        super(label, control);
    }
}
