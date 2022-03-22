package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.AddRequest;
import commands.Command;

/**
 * Update command. Updates data about the worker with the given ID.
 */
public class Update extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Update(WorkerColManager colManager) {
        super("update", "update the value of the collection" +
                " element whose id is equal to the given one");
        this.colManager = colManager;
    }

    /**
     * Updates data about the worker with the given ID. If ID is incorrect execution will be stopped.
     * Firstly removes worker with this ID, then adds new worker using AddRequest and
     * doesn't assign new ID to this worker.
     *
     * @param args the id of the updating worker
     */
    @Override
    public void action(String args) {
        int id = -1;
        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id of the worker");
            return;
        }
        if (!colManager.removeWorkerById(id)) {
            System.out.println("A worker with such id has not been found");
            return;
        }
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        worker.setId(id);
        colManager.addWorker(worker);
        System.out.println("Worker updated successfully!\n");
    }
}