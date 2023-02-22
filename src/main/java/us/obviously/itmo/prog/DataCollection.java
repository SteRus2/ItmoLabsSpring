package us.obviously.itmo.prog;

import us.obviously.itmo.prog.model.StudyGroup;

import java.util.Date;
import java.util.HashMap;

public class DataCollection {
    private HashMap<Integer, StudyGroup> data;
    private String type;
    private Date initDate;
    private int count;

    public DataCollection(DataReader dataReader){
        this.data = dataReader.getData();
        type = data.getClass().toString();
        initDate = new Date();
    }
    public DataInfo getInfo(){
        count = data.size();
        return new DataInfo(type, initDate, count);
    }
    public HashMap<Integer, StudyGroup> getData() {
        return data;
    }
}
