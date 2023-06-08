package us.obviously.itmo.prog.common.actions;

import us.obviously.itmo.prog.common.data.LocalDataCollection;
import us.obviously.itmo.prog.common.model.StudyGroup;

public class CheckGroupAction extends Action<Integer, StudyGroup> {

    public CheckGroupAction() {
        super("check");
    }

    @Override
    public Response execute(LocalDataCollection dataCollection, Integer arguments) {
        //Вот тут проблема, нужно понять не только, есть объект или нет, но и понять, владеет ли им пользователь, напомню, что у Action есть информация о юзере
        //getUserInfo()
        var exist = dataCollection.checkGroup(arguments);
        if (exist == null) {
            return new Response(getResponse().serialize(exist), ResponseStatus.OK);
        }
        boolean isMine = getDatabaseManager().checkUserObject(arguments, getUserInfo().getId());
        if (!isMine) {
            return new Response("Объект не принадлежит вам", ResponseStatus.UNAUTHORIZED);
        }
        return new Response(getResponse().serialize(exist), ResponseStatus.OK);
    }
}
