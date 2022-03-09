package Commands.concrete;

import Collection.WorkersCollection;
import Commands.Command;

public class Info extends Command {
    public Info(){
        super("info", "print information about the collection to standard output");
    }

    @Override
    public void action(WorkersCollection workers, String args){
        System.out.println("\nCollection type: "+ workers.getClass().getSimpleName() +"\nInitialization date: " + workers.getChangedDate()
                + "\nAmount of elements: " + workers.getSize() + "\n");
    }
}
