package commands.concrete;

import collection.WorkerColManager;
import commands.Command;

import java.util.Collection;

/**
 * Clear command. It removes all workers from the collection.
 */
public class Clear extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;
    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Clear(WorkerColManager colManager) {
        super("clear", "remove all workers from collection");
        this.colManager = colManager;
    }

    /**
     * Removes all workers from the given collection using removeAll method of super class.
     *
     * @param args    an empty line, as an imperfection of the program model
     * @see java.util.ArrayList#removeAll(Collection)
     */
    public void action(String args) {
        colManager.clear();
        System.out.println("Collection cleared successfully");
    }
}
