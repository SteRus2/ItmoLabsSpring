package us.obviously.itmo.prog.action_models;

import us.obviously.itmo.prog.actions.ResponseStatus;

public record ResponseModel(ResponseStatus status, String command, String body) {
}
