package us.obviously.itmo.prog.client.forms;

/**
 * Элементы меню формы
 *
 * @param description Человеческое описание элемента
 * @param value Значение
 * @param <T> Тип поля
 */
public record SelectChoice<T>(String description, T value) {
}
