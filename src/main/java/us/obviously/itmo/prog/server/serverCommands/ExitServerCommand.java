package us.obviously.itmo.prog.server.serverCommands;

import us.obviously.itmo.prog.server.exceptions.FailedToCloseServerException;
import us.obviously.itmo.prog.server.net.Server;

public class ExitServerCommand extends ServerCommand {


    public ExitServerCommand(Server server) {
        super("exit");
        this.targetServer = server;
    }

    @Override
    public void execute() {
        try {
            try {
                targetServer.data.saveData();
            } catch (Exception e) {
                targetServer.logger.severe("~reОшибка при сохранении коллекции: " + e.getMessage() + "~=");
            }
            targetServer.deactivateServer();
            System.exit(0);
        } catch (FailedToCloseServerException e) {
            targetServer.logger.severe("~reОшибка при закрытии сервера: " + e.getMessage() + "~=");
        }
    }
}
