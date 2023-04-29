package us.obviously.itmo.prog.server.serverCommands;

import us.obviously.itmo.prog.server.net.Server;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ServerCommandManager {
    private Server server;
    private Map<String, ServerCommand> commands;
    public ServerCommandManager(Server server){
        this.server = server;
        commands = new HashMap<>();
        var exitCommand = new ExitServerCommand(server);
        var saveCommand = new SaveServerCommand(server);
        commands.put(exitCommand.getName(), exitCommand);
        commands.put(saveCommand.getName(), saveCommand);
    }

    public ServerCommand getCommand(String name){
        return commands.get(name.toLowerCase().trim());
    }

    @Override
    public String toString() {
        return "ServerCommandManager{" +
                "server=" + server +
                ", commands=" + commands +
                '}';
    }
}
