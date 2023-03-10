package us.obviously.itmo.prog.commands;

import us.obviously.itmo.prog.console.ConsoleColors;
import us.obviously.itmo.prog.exceptions.UnexpectedArgumentException;
import us.obviously.itmo.prog.manager.Management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractCommand {
    Management manager;
    String name;
    String description;

    HashMap<String, Parameter> parameters;
    List<Parameter> indexParameters;

    public AbstractCommand(Management manager, String name, String description) {
        this.manager = manager;
        this.name = name;
        this.description = description;
        this.manager.addCommand(this);
        this.parameters = new HashMap<String, Parameter>();
        this.indexParameters = new ArrayList<Parameter>();
    }

    public abstract void execute(HashMap<String, String> args) throws Exception;

    final public void addParameter(String name, String description, Boolean required) {
        var parameter = new Parameter(name, description, required);
        parameters.put(name, parameter);
        indexParameters.add(parameter);
    }

    final public void addParameter(String name, String description) {
        var parameter = new Parameter(name, description, true);
        parameters.put(name, parameter);
        indexParameters.add(parameter);
    }

    final public String getDescription() {
        return String.format("%-40s%s", this.name, this.description);
    }

    final public String getHelp() {
        var builder = new StringBuilder();
        builder.append(this.description).append("%n%n".formatted());
        builder.append(this.name);
        this.parameters.forEach((key, param) -> {
            builder.append(" ");
            if (param.required)
                builder.append(param.name);
            else
                builder.append("[").append(param.name).append("]");
        });
        if (!this.parameters.isEmpty()) {
            builder.append("%n%n".formatted());
            this.parameters.forEach((key, param) -> {
                builder.append(param.getHelp()).append("%n".formatted());
            });
        }
        return builder.toString();
    }

    public String getKey() {
        return this.name;
    }

    public HashMap<String, String> parseParameters(String[] words) throws UnexpectedArgumentException {

        var maxCommandsNumber = indexParameters.size();
        var requiredCommandsNumber = indexParameters.stream().filter((x) -> {return x.required;}).count();
        var givenCommandsNumber = words.length;
        if (givenCommandsNumber > maxCommandsNumber) {
            throw new UnexpectedArgumentException(("?????????????? \"%s\" ?????????????? ???? ?????????? %d ????????????????????, " +
                    "???????????????? %d. ?????????????? " +
                    ConsoleColors.GREEN + "help %s" + ConsoleColors.RESET +
                    " ?????? ?????????????????? ?????????????????? ????????????????????.")
                    .formatted(this.name, maxCommandsNumber, givenCommandsNumber, this.name));
        }

        if (givenCommandsNumber < requiredCommandsNumber) {
            throw new UnexpectedArgumentException(("?????????????? \"%s\" ?????????????? %d ???????????????????????? ????????????????????, " +
                    "???????????????? %d. ?????????????? " +
                    ConsoleColors.GREEN + "help %s" + ConsoleColors.RESET +
                    " ?????? ?????????????????? ?????????????????? ????????????????????.")
                    .formatted(this.name, maxCommandsNumber, givenCommandsNumber, this.name));
        }

        var map = new HashMap<String, String>();
        for (int i = 0; i < words.length; i++) {
            var parameter = indexParameters.get(i);
            map.put(parameter.name, words[i]);
        }
        return map;
    }
}

