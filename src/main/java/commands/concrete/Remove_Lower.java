package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.AddRequest;
import commands.Command;


/**
 * Remove lower command. Removes all workers lower than the given one. Workers are compared by salary.
 *
 * @see Worker#compareTo(Worker)
 */
public class Remove_Lower extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Remove_Lower(WorkerColManager colManager) {
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
     * @see AddRequest
     */
    @Override
    public void action(String args) {
        Worker worker = new AddRequest().requestWorker();
        colManager.addWorker(worker);
        colManager.removeAllLower(worker);
        System.out.println("Workers removed successfully");
    }
}
