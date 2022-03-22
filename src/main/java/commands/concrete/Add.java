package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.AddRequest;
import commands.Command;

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
     * @see AddRequest
     */
    @Override
    public void action(String args) {
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        int i = 1;
        while (!colManager.addID(i)) {
            i += 1;
        }
        worker.setId(i);
        colManager.addWorker(worker);
        System.out.println("Worker added successfully!\n");
    }
}