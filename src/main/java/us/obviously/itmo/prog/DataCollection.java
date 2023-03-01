package us.obviously.itmo.prog;

import com.fasterxml.jackson.core.JsonProcessingException;
import us.obviously.itmo.prog.exceptions.NoSuchIdException;
import us.obviously.itmo.prog.exceptions.UsedKeyException;
import us.obviously.itmo.prog.model.StudyGroup;
import us.obviously.itmo.prog.reader.DataReader;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataCollection {
    private HashMap<Integer, StudyGroup> data;
    private String type;
    private Date initDate;
    private int count;

    public DataCollection(DataReader dataReader) throws JsonProcessingException {
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

    public void insertItem(StudyGroup item, int key) throws UsedKeyException{
        if (data.containsKey(key)){
            throw new UsedKeyException("К сожалению, ключ уже используется");
        } else {
            data.put(key, item);
        }
    }
    public void updateItem(StudyGroup item, int key) throws NoSuchIdException {
        if (!data.containsKey(key)){
            throw new NoSuchIdException("Объекта с таким id нет в коллекции");
        }
        data.put(key, item);
    }
    public void removeItem(int key){
        data.remove(key);
    }
    public void clearData(){
        data.clear();
    }

}
