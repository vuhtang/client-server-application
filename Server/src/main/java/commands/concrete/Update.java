package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;

import java.util.ArrayList;
import java.util.List;

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
    public List<String> action(String args, Worker worker) {
        List<String> response = new ArrayList<>();
        int id;
        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            response.add("Invalid id of the worker");
            return response;
        }
        if (!colManager.removeWorkerById(id)) {
            response.add("A worker with such id has not been found");
            return response;
        }
        if (worker == null) {
            response.add("Unable to update worker");
            return response;
        }
        worker.setId(id);
        colManager.addWorker(worker);
        response.add("Worker updated successfully!");
        return response;
    }
}