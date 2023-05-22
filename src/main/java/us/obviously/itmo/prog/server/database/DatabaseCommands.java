package us.obviously.itmo.prog.server.database;

public final class DatabaseCommands {
    public static final String getAll = "select * from study_group join persons on study_group.group_admin = persons.person_id";
}
