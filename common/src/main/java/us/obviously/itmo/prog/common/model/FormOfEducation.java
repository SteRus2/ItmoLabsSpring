package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION("Дистанционное обучение", "distanceEduction"),
    FULL_TIME_EDUCATION("Полное очное обучение", "fullTimeEducation"),
    EVENING_CLASSES("Вечернее обучение", "eveningClasses");

    public final String name;
    public final String key;

    FormOfEducation(String name, String key) {
        this.name = name;
        this.key = key;
    }
}
