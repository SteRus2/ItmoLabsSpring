package us.obviously.itmo.prog.commands;

public class Parameter {
    String name;
    String description;
    Boolean required;

    public Parameter(String name, String description, Boolean required) {
        this.name = name;
        this.description = description;
        this.required = required;
    }

    public String getHelp() {
        return String.format("\t%s - %s", this.name, this.description);
    }
}
