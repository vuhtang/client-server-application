package Commands.concrete;

import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.AddRequest;
import Commands.Command;

public class Insert_At extends Command {
    public Insert_At(){
        super("insert_at", "add a new worker to a given position");
    }

    @Override
    public void action(WorkersCollection workers, String args) {
        int index = -1;
        try{
            index = Integer.parseInt(args);
            if (index < 0 || index > workers.getSize()){
                System.out.println("Invalid index");
                return;
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid index");
            return;
        }
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        int i = 1;
        while(!workers.addID(i)){
            i+=1;
        }
        worker.setId(i);
        workers.add(index, worker);
        System.out.println("Worker added successfully!\n");
    }
}
