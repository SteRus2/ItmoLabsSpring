package us.obviously.itmo.prog.client.forms;

import us.obviously.itmo.prog.client.manager.Management;

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
