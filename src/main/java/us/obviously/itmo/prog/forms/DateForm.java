package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.exceptions.FormInterruptException;
import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.fields.IntegerFormField;
import us.obviously.itmo.prog.manager.Management;

import java.time.ZonedDateTime;


/**
 * Форма Даты
 */
public class DateForm extends Form<ZonedDateTime> {
    Integer year = null;
    Integer month = null;
    Integer day = null;
    Integer hours = null;
    Integer minutes = null;
    Integer[] days = new Integer[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public DateForm(Management manager) {
        super(manager);
    }


    /**
     * Форма создания новой Даты
     */
    public void create() {
        try {
            new IntegerFormField(manager, "year", this::setYear).run();
            new IntegerFormField(manager, "month", this::setMonth).run();
            new IntegerFormField(manager, "day", this::setDay).run();
            new IntegerFormField(manager, "offset hours", this::setHours, false, 0, null).run();
            new IntegerFormField(manager, "offset minutes", this::setMinutes, false, 0, null).run();
        } catch (FormInterruptException ignored) {
        }
    }

    /**
     * Валидация года и сохранение в переменную
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setYear(Integer value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле year не может быть null.");
        if (value > 2023) throw new IncorrectValueException("Нельзя было родиться после этого года.");
        if (value < 1900) throw new IncorrectValueException("У нас тип unix и тут так мало нельзя.");
        this.year = value;
    }

    /**
     * Валидация месяця и сохранение в переменную
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setMonth(Integer value) throws IncorrectValueException {
        if (value == null) throw new IncorrectValueException("Поле month не может быть null.");
        if (value > 12) throw new IncorrectValueException("В году двенадцать месяцев.");
        if (value < 1) throw new IncorrectValueException("В году двенадцать месяцев.");
        this.month = value;
    }

    /**
     * Валидация дня и сохранение в переменную
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setDay(Integer value) throws IncorrectValueException {
        assert year != null && month != null;
        Integer maxDays = days[month - 1];
        if (month == 2 && year % 4 == 0) maxDays = 29;
        if (value == null) throw new IncorrectValueException("Поле day не может быть null.");
        if (value > maxDays) throw new IncorrectValueException("В месяце %d дня.".formatted(maxDays));
        if (value < 1) throw new IncorrectValueException("В месяце %d натуральных или ноль дня.".formatted(maxDays));
        this.day = value;
    }

    /**
     * Валидация часов и сохранение в переменную
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setHours(Integer value) throws IncorrectValueException {
        if (value == null) {
            this.hours = 0;
            return;
        }
        if (value > 23) throw new IncorrectValueException("В дне 24 часа.");
        if (value < 0) throw new IncorrectValueException("В дне 24 натуральных или ноль часа.");
        this.hours = value;
    }

    /**
     * Валидация минут и сохранение в переменную
     *
     * @throws IncorrectValueException Выбросит исключение, если поле невалидно
     */
    public void setMinutes(Integer value) throws IncorrectValueException {
        if (value == null) {
            this.minutes = 0;
            return;
        }
        if (value > 59) throw new IncorrectValueException("В часу 60 минут.");
        if (value < 0) throw new IncorrectValueException("В часу 60 натуральных или ноль минут.");
        this.minutes = value;
    }

    /**
     * Валидация готовой формы и сборка новой Даты
     *
     * @return Готовая Дата
     */
    public ZonedDateTime build() {
        return ZonedDateTime.parse("%04d-%02d-%02dT00:00:00+%02d:%02d".formatted(year, month, day, hours, minutes));
    }
}
