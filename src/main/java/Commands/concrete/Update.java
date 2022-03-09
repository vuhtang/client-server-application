package Commands.concrete;

import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.AddRequest;
import Commands.Command;


public class Update extends Command {
    public Update(){
        super("update", "update the value of the collection" +
                " element whose id is equal to the given one");
    }
    @Override
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
            return;
        }
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        worker.setId(id);
        workers.add(worker);
        System.out.println("Worker updated successfully!\n");
    }
}