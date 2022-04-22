package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Add command. It adds new worker to the given collection,
 * worker data is entered in the console sequentially.
 */
public class Add extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Add(WorkerColManager colManager) {
        super("add", "add a new element to the collection");
        this.colManager = colManager;
    }

    /**
     * Adds new worker to the given collection. During work, it makes a request to enter
     * data about the worker through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param args an empty line, as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> response = new ArrayList<>();
        if (worker == null) {
            response.add("Unable to add worker");
            return response;
        }
        int i = 1;
        while (!colManager.addID(i)) {
            i += 1;
        }
        worker.setId(i);
        colManager.addWorker(worker);
        response.add("Worker added successfully!");
        return response;
    }


}