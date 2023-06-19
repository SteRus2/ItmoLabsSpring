package us.obviously.itmo.prog.common.server.serverCommands;

import us.obviously.itmo.prog.common.console.ConsoleColor;
import us.obviously.itmo.prog.common.server.net.Server;

public abstract class ServerCommand {
    static {
        ConsoleColor.initColors();
    }

    private final String name;
    protected Server targetServer;

    public abstract void execute();

    public ServerCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
