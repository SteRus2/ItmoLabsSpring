package us.obviously.itmo.prog.server.serverCommands;

import us.obviously.itmo.prog.client.console.ConsoleColor;
import us.obviously.itmo.prog.client.console.Messages;
import us.obviously.itmo.prog.server.exceptions.CantWriteDataException;
import us.obviously.itmo.prog.server.exceptions.FailedToDumpsEx;
import us.obviously.itmo.prog.server.exceptions.FileNotWritableException;
import us.obviously.itmo.prog.server.net.Server;
import us.obviously.itmo.prog.server.exceptions.FailedToCloseServerException;

public class ExitServerCommand extends ServerCommand{


    public ExitServerCommand(Server server) {
        super("exit");
        this.targetServer = server;
    }

    @Override
    public void execute() {
        try {
            try {
                targetServer.data.saveData();
            }  catch (Exception e){
                targetServer.logger.severe("~reОшибка при сохранении коллекции: " + e.getMessage() + "~=");
            }
            targetServer.deactivateServer();
            System.exit(0);
        } catch (FailedToCloseServerException e) {
            targetServer.logger.severe("~reОшибка при закрытии сервера: " + e.getMessage() + "~=");
        }
    }
}
