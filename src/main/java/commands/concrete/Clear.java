package commands.concrete;

import collection.WorkersCollection;
import commands.Command;

import java.util.Collection;

/**
 * Clear command. It removes all workers from the collection.
 */
public class Clear extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Clear() {
        super("clear", "remove all workers from collection");
    }

    /**
     * Removes all workers from the given collection using removeAll method of super class.
     *
     * @param workers the collection of workers to remove elements in
     * @param args    an empty line, as an imperfection of the program model
     * @see java.util.ArrayList#removeAll(Collection)
     */
    public void action(WorkersCollection workers, String args) {
        workers.removeAll(workers);
    }
}
