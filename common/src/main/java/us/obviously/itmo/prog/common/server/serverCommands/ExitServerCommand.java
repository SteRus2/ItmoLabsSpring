package us.obviously.itmo.prog.common.server.serverCommands;

import us.obviously.itmo.prog.common.server.exceptions.FailedToCloseServerException;
import us.obviously.itmo.prog.common.server.net.Server;

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
                Server.logger.severe("~reОшибка при сохранении коллекции: " + e.getMessage() + "~=");
            }
            targetServer.deactivateServer();
            System.exit(0);
        } catch (FailedToCloseServerException e) {
            Server.logger.severe("~reОшибка при закрытии сервера: " + e.getMessage() + "~=");
        }
    }
}
