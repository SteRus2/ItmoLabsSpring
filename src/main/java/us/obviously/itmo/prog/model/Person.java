package us.obviously.itmo.prog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Person implements Comparable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")

    private java.time.ZonedDateTime birthday; //Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null

    public static Builder newBuilder() {
        return new Person().new Builder();
    }

    public Country getNationality() {
        return nationality;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    /**
     * Позволяет сравнивать админов, сначала по имени, потом по возрасту
     * @param o the object to be compared.
     * @return Число, говорящее о том, какой админ "больше"
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Person)) {
            return 1;
        }

        if (!this.name.equals(((Person) o).getName())) {
            return this.name.compareToIgnoreCase(((Person) o).getName());
        }
        if (!this.birthday.equals(((Person) o).getBirthday())) {
            return this.birthday.compareTo(((Person) o).getBirthday());
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(birthday, person.birthday) && eyeColor == person.eyeColor && hairColor == person.hairColor && nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, eyeColor, hairColor, nationality);
    }

    public class Builder {
        public Builder setName(String name) {
            Person.this.name = name;

            return this;
        }

        public Builder setBirthday(ZonedDateTime birthday) {
            Person.this.birthday = birthday;

            return this;
        }

        public Builder setEyeColor(Color eyeColor) {
            Person.this.eyeColor = eyeColor;

            return this;
        }

        public Builder setHairColor(Color hairColor) {
            Person.this.hairColor = hairColor;

            return this;
        }

        public Builder setNationality(Country nationality) {
            Person.this.nationality = nationality;

            return this;
        }

        public Person build() {
            return Person.this;
        }
    }
}
