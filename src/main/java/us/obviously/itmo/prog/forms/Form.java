package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.manager.Management;

public abstract class Form<T> {

    Management manager;

    public Form(Management manager) {
        this.manager = manager;
    }
}
