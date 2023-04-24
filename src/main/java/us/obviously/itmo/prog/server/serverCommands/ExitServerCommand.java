package us.obviously.itmo.prog.server.serverCommands;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.server.net.Server;
import us.obviously.itmo.prog.server.exceptions.FailedToCloseServerException;

public class ExitServerCommand extends ServerCommand{

    static {
        ConsoleColor.initColors();
    }

    public ExitServerCommand(Server server) {
        super("exit");
        this.targetServer = server;
    }

    @Override
    public void execute() {
        try {
            targetServer.deactivateServer();
        } catch (FailedToCloseServerException e) {
            Messages.printStatement("~reОшибка при закрытии сервера: " + e.getMessage() + "~=");
        }
        System.exit(0);
    }
}
