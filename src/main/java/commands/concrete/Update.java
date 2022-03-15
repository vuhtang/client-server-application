package commands.concrete;

import collection.entity.Worker;
import collection.WorkersCollection;
import commands.AddRequest;
import commands.Command;

/**
 * Update command. Updates data about the worker with the given ID.
 */
public class Update extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Update() {
        super("update", "update the value of the collection" +
                " element whose id is equal to the given one");
    }

    /**
     * Updates data about the worker with the given ID. If ID is incorrect execution will be stopped.
     * Firstly removes worker with this ID, then adds new worker using AddRequest and
     * doesn't assign new ID to this worker.
     *
     * @param workers
     * @param args
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        int id = -1;
        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id of the worker");
            return;
        }
        for (Worker worker : workers) {
            if (worker.getId() == id) workers.remove(worker);
        }
        if (id == -1) {
            System.out.println("A worker with such id has not been found");
            return;
        }
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        worker.setId(id);
        workers.add(worker);
        System.out.println("Worker updated successfully!\n");
    }
}