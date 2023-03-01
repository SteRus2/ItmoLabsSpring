package us.obviously.itmo.prog;

import java.util.Date;

public class DataInfo {
    private String type;   // Тип данных
    private Date date;     // Дата инициализации данных
    private Integer count; // Количество элементов

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
