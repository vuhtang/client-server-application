package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;

import java.util.ArrayList;
import java.util.List;


/**
 * Remove lower command. Removes all workers lower than the given one. Workers are compared by salary.
 *
 * @see Worker#compareTo(Worker)
 */
public class RemoveLower extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveLower(WorkerColManager colManager) {
        super("remove_lower", "remove from the collection all elements " +
                "smaller than the given one");
        this.colManager = colManager;
    }

    /**
     * Removes all workers lower than the given one. Firstly adds new worker to the collection
     * using AddRequest and doesn't assign new ID to this worker. Then removes elements with
     * this given worker using removeAll method of ArrayList.
     *
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> response = new ArrayList<>();
        colManager.addWorker(worker);
        colManager.removeAllLower(worker);
        response.add("Workers removed successfully");
        return response;
    }
}
