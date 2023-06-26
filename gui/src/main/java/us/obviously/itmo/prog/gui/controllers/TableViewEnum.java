package us.obviously.itmo.prog.gui.controllers;

public enum TableViewEnum {
    STUDY_GROUP("tables.studyGroup"),
    STUDY_GROUP_GROUP_BY_NAME("tables.studyGroupGroupByName"),
    SEMESTER("tables.semester");

    final String key;

    TableViewEnum(String key) {
        this.key = key;
    }
}
