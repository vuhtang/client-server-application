package commands.concrete;

import collection.WorkersCollection;
import commands.Command;

/**
 * Info command. Displays type, initialization date and amount of elements of the collection.
 */
public class Info extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Info() {
        super("info", "print information about the collection to standard output");
    }

    /**
     * Displays information about the given collection.
     *
     * @param workers the collection to work with
     * @param args    an empty string as an imperfection of the program model
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        System.out.println("\ncollection type: " + workers.getClass().getSimpleName()
                + "\nInitialization date: " + workers.getChangedDate()
                + "\nAmount of elements: " + workers.size() + "\n");
    }
}
