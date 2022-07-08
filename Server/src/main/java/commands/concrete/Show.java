package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;
import transferring.Token;

import java.util.List;

/**
 * Show command. Displays all collection elements with their characteristics.
 */
public class Show extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    public Show(WorkerColManager colManager) {
        super("show", "print all elements of collection to standard output");
        this.colManager = colManager;
    }

    /**
     * Shows all collection elements with values of all their fields.
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        return colManager.showWorkers();
    }
}
