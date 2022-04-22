package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;

import java.util.List;

/**
 * Show command. Displays all collection elements with their characteristics.
 */
public class Show extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Show(WorkerColManager colManager) {
        super("show", "print all elements of collection to standard output");
        this.colManager = colManager;
    }

    /**
     * Shows all collection elements with values of all their fields.
     *
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker) {
        return colManager.showWorkers();
    }
}
