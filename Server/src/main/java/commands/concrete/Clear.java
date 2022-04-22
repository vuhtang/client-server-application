package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
     * @param args an empty line, as an imperfection of the program model
     * @see java.util.ArrayList#removeAll(Collection)
     */
    @Override
    public List<String> action(String args, Worker worker) {
        colManager.clear();
        List<String> response = new ArrayList<>();
        response.add("Collection cleared successfully");
        return response;
    }
}
