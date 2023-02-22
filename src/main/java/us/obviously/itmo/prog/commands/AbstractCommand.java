package us.obviously.itmo.prog.commands;

public abstract class AbstractCommand {
    private String description;
    private String name;
    abstract void execute();
}
