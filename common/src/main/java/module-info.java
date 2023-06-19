module us.obviously.itmo.prog.common {
    requires com.auth0.jwt;
    requires java.sql;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;

    exports us.obviously.itmo.prog.common;
    exports us.obviously.itmo.prog.common.actions;
    exports us.obviously.itmo.prog.common.action_models;
    exports us.obviously.itmo.prog.common.console;
    exports us.obviously.itmo.prog.common.model;
    exports us.obviously.itmo.prog.common.validation;
    exports us.obviously.itmo.prog.common.serializers;
    exports us.obviously.itmo.prog.common.server;
    exports us.obviously.itmo.prog.common.server.data;
    exports us.obviously.itmo.prog.common.server.exceptions;
}