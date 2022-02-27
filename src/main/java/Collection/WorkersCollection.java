package Collection;

import Collection.Entity.Worker;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WorkersCollection extends ArrayList<Worker> {
    private final String filePath;
    private String changedDate;
    {
        changedDate = LocalDateTime.now().toString();
    }

    public WorkersCollection(String path){
        super();
        filePath = path;
    }
    public String getFilePath(){return filePath;}
    public void setChangedDate(){
        changedDate = LocalDateTime.now().toString();
    }
    public String getChangedDate(){
        return changedDate;
     }
}
