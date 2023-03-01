package us.obviously.itmo.prog.forms;

import us.obviously.itmo.prog.exceptions.IncorrectValueException;
import us.obviously.itmo.prog.fields.DateField;
import us.obviously.itmo.prog.fields.StringField;
import us.obviously.itmo.prog.model.Person;

public class PersonFormFields {
    StringField name;
    StringField eyeColor;
    StringField hairColor;
    StringField nationality;
    DateField creationDate;
    private Person.Builder builder;

    public PersonFormFields() {
        this.builder = Person.newBuilder();
        this.name = new StringField();
        this.eyeColor = new StringField().setChoices(new String[]{"R", "Y", "O", "W"});
        this.hairColor = new StringField().setChoices(new String[]{"R", "Y", "O", "W"});
        this.nationality = new StringField().setChoices(new String[]{"F", "I", "V", "T", "J"});
        this.creationDate = new DateField(); // TODO: DATE FIELD
    }

    public void setName(String name) throws IncorrectValueException {
        this.name.check(name);
        this.builder.setName(name);
    }

    public Person build() {
        return this.builder.build();
    }
}
