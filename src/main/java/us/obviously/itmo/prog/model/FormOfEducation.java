package us.obviously.itmo.prog.model;

public enum FormOfEducation {
    DISTANCE_EDUCATION ("Дистанционное обучение"),
    FULL_TIME_EDUCATION ("Полное очное обучение"),
    EVENING_CLASSES ("Вечернее обучение");

    public final String name;

    FormOfEducation(String name) {
        this.name = name;
    }
}
