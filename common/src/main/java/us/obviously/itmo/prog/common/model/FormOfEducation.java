package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION("Дистанционное обучение", "formOfEducation.distanceEduction"),
    FULL_TIME_EDUCATION("Полное очное обучение", "formOfEducation.fullTimeEducation"),
    EVENING_CLASSES("Вечернее обучение", "formOfEducation.eveningClasses");

    public final String name;
    public final String key;

    FormOfEducation(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public static FormOfEducation[] getOptions() {
        return new FormOfEducation[]{
                FULL_TIME_EDUCATION,
                EVENING_CLASSES,
                DISTANCE_EDUCATION,
        };
    }
}
