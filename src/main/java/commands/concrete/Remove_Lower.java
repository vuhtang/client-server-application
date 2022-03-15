package commands.concrete;

import collection.entity.Worker;
import collection.WorkersCollection;
import commands.AddRequest;
import commands.Command;

import java.util.Collections;
import java.util.List;

/**
 * Remove lower command. Removes all workers lower than the given one. Workers are compared by salary.
 *
 * @see Worker#compareTo(Worker)
 */
public class Remove_Lower extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Remove_Lower() {
        super("remove_lower", "remove from the collection all elements " +
                "smaller than the given one");
    }

    /**
     * Removes all workers lower than the given one. Firstly adds new worker to the collection
     * using AddRequest and doesn't assign new ID to this worker. Then removes elements using
     * removeAll method of super class.
     *
     * @param workers the collection to work with
     * @param args    an empty string as an imperfection of the program model
     * @see AddRequest
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        Worker worker = new AddRequest().requestWorker();
        workers.add(worker);
        Collections.sort(workers);
        List<Worker> list = workers.stream().filter((p) -> p.compareTo(worker) <= 0).toList();
        workers.removeAll(list);
    }
}
