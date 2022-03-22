package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.AddRequest;
import commands.Command;

/**
 * Insert at command. Inserts new worker at the position as a collection index.
 */
public class Insert_At extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Insert_At(WorkerColManager colManager) {
        super("insert_at", "add a new worker to a given position");
        this.colManager = colManager;
    }

    /**
     * Adds new worker to a given position. If the position is incorrect execution will be stopped.
     * During work, it makes a request to enter data about the worker
     * through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param args the index to insert at
     * @see AddRequest
     */
    @Override
    public void action(String args) {
        int index = -1;
        try {
            index = Integer.parseInt(args);
            if (index < 0 || index > colManager.getSize()) {
                System.out.println("Invalid index");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid index");
            return;
        }
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        int i = 1;
        while (!colManager.addID(i)) {
            i += 1;
        }
        worker.setId(i);
        colManager.insertAt(index, worker);
        System.out.println("Worker added successfully!\n");
    }
}
