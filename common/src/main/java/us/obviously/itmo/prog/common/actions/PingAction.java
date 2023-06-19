package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.action_models.VoidModel;
import us.obviously.itmo.prog.common.server.data.LocalDataCollection;

import static us.obviously.itmo.prog.common.actions.ResponseStatus.OK;

public class PingAction extends Action<VoidModel, VoidModel>{
    public PingAction() {
        super("ping");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, VoidModel arguments) {
        return new Response("Пинг сервера", OK);
    }
}
