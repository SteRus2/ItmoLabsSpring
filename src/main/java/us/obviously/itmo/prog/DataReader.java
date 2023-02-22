package us.obviously.itmo.prog;

import us.obviously.itmo.prog.model.StudyGroup;

import java.util.HashMap;

public abstract class DataReader {
    abstract HashMap<Integer, StudyGroup> getData();
}
