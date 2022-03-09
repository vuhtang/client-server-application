package Collection;

import Collection.Entity.Worker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class WorkersCollection extends ArrayList<Worker> {
    private final String filePath;
    private final LocalDateTime initializationDate;
    private HashSet<Integer> idSet = new HashSet<>();
    {
        initializationDate = LocalDateTime.now();
    }

    public WorkersCollection(String path){
        super();
        filePath = path;
    }
    @Override
    public boolean addAll(Collection<? extends Worker> c){
        for (Worker worker: c){
            idSet.add(worker.getId());
        }
        return super.addAll(c);
    }
    public boolean addID(int id){
        return idSet.add(id);
    }

    public String getFilePath(){return filePath;}
    public LocalDateTime getChangedDate(){
        return initializationDate;
    }
    public int getSize(){
        return this.size();
    }
}
