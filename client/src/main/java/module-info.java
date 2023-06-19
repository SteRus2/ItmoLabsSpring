module us.obviously.itmo.prog.client {
    requires us.obviously.itmo.prog.common;
    requires com.auth0.jwt;

    exports us.obviously.itmo.prog.client;
    exports us.obviously.itmo.prog.client.manager;
    exports us.obviously.itmo.prog.client.commands;
}