package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert at command. Inserts new worker at the position as a collection index.
 */
public class InsertAt extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public InsertAt(WorkerColManager colManager) {
        super("insert_at", "add a new worker to a given position");
        this.colManager = colManager;
    }

    /**
     * Adds new worker to a given position. If the position is incorrect execution will be stopped.
     * During work, it makes a request to enter data about the worker
     * through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param args the index to insert at
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> response = new ArrayList<>();
        int index;
        try {
            index = Integer.parseInt(args);
            if (index < 0 || index > colManager.getSize()) {
                response.add("Invalid index (collection size: [0, " + (colManager.getSize() - 1) + "])");
                return response;
            }
        } catch (NumberFormatException e) {
            response.add("Invalid index");
            return response;
        }
        if (worker == null) {
            response.add("Unable to add worker(it's null)");
            return response;
        }
        int i = 1;
        while (!colManager.addID(i)) {
            i += 1;
        }
        worker.setId(i);
        colManager.insertAt(index, worker);
        response.add("Worker added successfully!");
        return response;
    }
}
