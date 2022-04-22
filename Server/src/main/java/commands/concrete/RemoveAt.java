package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove at command. Removes a worker at the position as a collection index.
 */
public class RemoveAt extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveAt(WorkerColManager colManager) {
        super("remove_at", "remove an element at a give position");
        this.colManager = colManager;
    }

    /**
     * Removes a worker at a given position. If the position is incorrect execution will be stopped.
     * Uses remove method of super class.
     *
     * @param args the index to remove at
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> response = new ArrayList<>();
        int index;
        try {
            index = Integer.parseInt(args);
            if (index < 0 || index >= colManager.getSize()) {
                response.add("Invalid index");
                return response;
            }
        } catch (NumberFormatException e) {
            response.add("Invalid index");
            return response;
        }
        colManager.removeWorkerAt(index);
        response.add("Worker removed successfully!");
        return response;
    }
}
