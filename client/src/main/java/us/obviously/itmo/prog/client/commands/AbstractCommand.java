package us.obviously.itmo.prog.client.commands;

import us.obviously.itmo.prog.client.manager.Management;
import us.obviously.itmo.prog.common.server.exceptions.ExecuteCommandException;
import us.obviously.itmo.prog.common.server.exceptions.MissedArgumentException;
import us.obviously.itmo.prog.common.server.exceptions.UnexpectedArgumentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Команда для взаимодействия пользователя с программой
 */
public abstract class AbstractCommand {
    final Management manager;
    final String name;
    final String description;

    final HashMap<String, Parameter> parameters;
    final List<Parameter> indexParameters;

    public AbstractCommand(Management manager, String name, String description) {
        this.manager = manager;
        this.name = name;
        this.description = description;
        this.manager.addCommand(this);
        this.parameters = new HashMap<>();
        this.indexParameters = new ArrayList<>();
    }

    /**
     * Вызов команды, которая получает на вход мапу аргументов
     *
     * @param args Аргументы, не провалидированные
     */
    public abstract void execute(HashMap<String, String> args) throws ExecuteCommandException;

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
        return String.format("~gr%-40s~=%s", this.name, this.description);
    }

    final public String getHelp() {
        var builder = new StringBuilder();
        builder.append(this.description).append("%n%n".formatted());
        builder.append("~gr").append(this.name).append("~=");
        this.parameters.forEach((key, param) -> {
            builder.append(" ");
            if (param.required)
                builder.append("~ye").append(param.name).append("~=");
            else
                builder.append("~0bk").append("[").append(param.name).append("]").append("~=");
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

    public HashMap<String, String> parseParameters(String[] words) throws UnexpectedArgumentException, MissedArgumentException {

        var maxCommandsNumber = indexParameters.size();
        var requiredCommandsNumber = indexParameters.stream().filter((x) -> {
            return x.required;
        }).count();
        var givenCommandsNumber = words.length;
        if (givenCommandsNumber > maxCommandsNumber) {
            throw new UnexpectedArgumentException(("Команда \"%s\" ожидала не более %d параметров, " +
                    "передано %d. Введите " +
                    "~grhelp %s~=" +
                    " для просмотра возможных параметров.")
                    .formatted(this.name, maxCommandsNumber, givenCommandsNumber, this.name));
        }

        if (givenCommandsNumber < requiredCommandsNumber) {
            throw new MissedArgumentException(("Команда \"%s\" ожидала %d обязательных параметров, " +
                    "передано %d. Введите " +
                    "~grhelp %s~=" +
                    " для просмотра возможных параметров.")
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

