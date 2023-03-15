package us.obviously.itmo.prog;

import java.util.Date;

/**
 * Класс, предоставляющий общую информацию о хранимых данных
 * @author stepa
 */
public class DataInfo {
    /** Поле тип данных, хранимых в коллекции */
    private String type;
    /** Поле дата инициализации данных */
    private Date date;
    /** Поле количество записей в коллекции */
    private Integer count;

    public DataInfo(String type, Date date, Integer count) {
        this.type = type;
        this.date = date;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }


    public Integer getCount() {
        return count;
    }
}
