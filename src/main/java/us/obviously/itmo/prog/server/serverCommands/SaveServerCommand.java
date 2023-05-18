package us.obviously.itmo.prog.server.serverCommands;

import us.obviously.itmo.prog.server.net.Server;

public class SaveServerCommand extends ServerCommand{
    public SaveServerCommand(Server server) {
        super("save");
        this.targetServer = server;
    }

    @Override
    public void execute() {
        try {
            targetServer.data.saveData();
            targetServer.logger.info("Коллекция сохранена");
        }  catch (Exception e){
            targetServer.logger.severe("~reОшибка при сохранении коллекции: " + e.getMessage() + "~=");
        }
    }
}
