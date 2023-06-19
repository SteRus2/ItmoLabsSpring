package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.manager.Management;

/**
 * Форма для заполнения полей модели <b>T</b>
 *
 * @param <T> Тип модели
 */
public abstract class Form<T> {

    final Management manager;

    public Form(Management manager) {
        this.manager = manager;
    }
}
