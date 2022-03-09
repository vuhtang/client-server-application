package Commands.concrete;

import Collection.Entity.*;
import Collection.WorkersCollection;
import Commands.AddRequest;
import Commands.Command;


public class Add extends Command{
    public Add(){
        super("add", "add a new element to the collection");
    }
    @Override
    public void action(WorkersCollection workers, String args){
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        int i = 1;
        while(!workers.addID(i)){
            i+=1;
        }
        worker.setId(i);
        workers.add(worker);
        System.out.println("Worker added successfully!\n");
    }
}