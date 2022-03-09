package Commands.concrete;

import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.Command;

public class Remove_By_ID extends Command {
    public Remove_By_ID(){
        super("remove_by_id", "remove worker by id");
    }
    public void action(WorkersCollection workers, String args){
        int id = -1;
        try{
            id = Integer.parseInt(args);
        } catch (NumberFormatException e){
            System.out.println("Invalid id of the worker");
            return;
        }
        for (Worker worker: workers){
            if (worker.getId() == id) workers.remove(worker);
        }
        if (id == -1){
            System.out.println("A worker with such id has not been found");
        } else System.out.println("Worker removed successfully!\n");
    }
}
