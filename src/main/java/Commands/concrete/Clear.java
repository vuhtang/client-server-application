package Commands.concrete;

import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.Command;

public class Clear extends Command {
    public Clear(){
        super("clear", "remove all workers from collection");
    }
    public void action(WorkersCollection workers, String args){
        workers.removeAll(workers);
    }
}
