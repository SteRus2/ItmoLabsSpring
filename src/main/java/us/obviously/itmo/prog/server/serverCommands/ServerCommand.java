package us.obviously.itmo.prog.server.serverCommands;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.server.net.Server;

public abstract class ServerCommand {
    static {
        ConsoleColor.initColors();
    }

    private String name;
    protected Server targetServer;

    public abstract void execute();

    public ServerCommand(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
