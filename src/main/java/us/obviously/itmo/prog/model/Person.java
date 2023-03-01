package us.obviously.itmo.prog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")

    private java.time.ZonedDateTime birthday; //Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null

    public Person(){}
    public Person(String name, ZonedDateTime birthday, Color eyeColor, Color hairColor, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }
}
