package us.obviously.itmo.prog.common.model;

import java.io.Serializable;

public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION("Дистанционное обучение"),
    FULL_TIME_EDUCATION("Полное очное обучение"),
    EVENING_CLASSES("Вечернее обучение");

    public final String name;

    FormOfEducation(String name) {
        this.name = name;
    }
}
