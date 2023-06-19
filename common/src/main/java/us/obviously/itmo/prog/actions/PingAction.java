package us.obviously.itmo.prog.actions;

import us.obviously.itmo.prog.action_models.VoidModel;
import us.obviously.itmo.prog.server.data.LocalDataCollection;

import static us.obviously.itmo.prog.actions.ResponseStatus.OK;

public class PingAction extends Action<VoidModel, VoidModel>{
    public PingAction() {
        super("ping");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        return new Response("Пинг сервера", OK);
    }
}
