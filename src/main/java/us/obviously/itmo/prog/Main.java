package us.obviously.itmo.prog;

import us.obviously.itmo.prog.manager.Management;
import us.obviously.itmo.prog.manager.Manager;
import us.obviously.itmo.prog.model.StudyGroup;

public class Main {
    public static void main(String[] args) {
        DataReader reader;
        Management manager = new Manager<StudyGroup>();
        manager.run();
    }
}