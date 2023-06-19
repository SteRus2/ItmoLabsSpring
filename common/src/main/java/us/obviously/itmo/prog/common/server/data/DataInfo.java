package us.obviously.itmo.prog.common.server.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс, предоставляющий общую информацию о хранимых данных
 *
 * @author stepa
 */
public class DataInfo implements Serializable {
    /**
     * Поле тип данных, хранимых в коллекции
     */
    private final String type;
    /**
     * Поле дата инициализации данных
     */
    private final Date date;
    /**
     * Поле количество записей в коллекции
     */
    private final Integer count;

    /**
     * Конструктор, задающий информацию оо коллекции
     *
     * @param type  Тип данных
     * @param date  Дата инициализации
     * @param count Количество записей
     */
    public DataInfo(String type, Date date, Integer count) {
        this.type = type;
        this.date = date;
        this.count = count;
    }

    /**
     * @return Тип данных
     */
    public String getType() {
        return type;
    }

    /**
     * @return Дата инициализации
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return Количество записей
     */
    public Integer getCount() {
        return count;
    }
}
