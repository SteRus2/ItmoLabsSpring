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
        if (required) {
            return String.format("\t~ye%s~= - %s", this.name, this.description);
        }
        return String.format("\t~0bk%s~= - %s", this.name, this.description);
    }
}
