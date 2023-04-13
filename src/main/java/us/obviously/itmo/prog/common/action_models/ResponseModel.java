package us.obviously.itmo.prog.common.action_models;

import us.obviously.itmo.prog.common.actions.ResponseStatus;

public record ResponseModel(ResponseStatus status, String command, String body) {
}
