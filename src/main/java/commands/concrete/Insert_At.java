package commands.concrete;

import collection.entity.Worker;
import collection.WorkersCollection;
import commands.AddRequest;
import commands.Command;

/**
 * Insert at command. Inserts new worker at the position as a collection index.
 */
public class Insert_At extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Insert_At() {
        super("insert_at", "add a new worker to a given position");
    }

    /**
     * Adds new worker to a given position. If the position is incorrect execution will be stopped.
     * During work, it makes a request to enter data about the worker
     * through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param workers the collection of workers to compute value of
     * @param args    the index to insert at
     * @see AddRequest
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        int index = -1;
        try {
            index = Integer.parseInt(args);
            if (index < 0 || index > workers.size()) {
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
        while (!workers.addID(i)) {
            i += 1;
        }
        worker.setId(i);
        workers.add(index, worker);
        System.out.println("Worker added successfully!\n");
    }
}
