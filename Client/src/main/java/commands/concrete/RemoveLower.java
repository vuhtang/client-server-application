package commands.concrete;

import collection.entity.Worker;
import commands.AddRequest;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;


/**
 * Remove lower command. Removes all workers lower than the given one. Workers are compared by salary.
 *
 * @see Worker#compareTo(Worker)
 */
public class RemoveLower extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveLower(Transfer transfer) {
        super("remove_lower", "remove from the collection all elements " +
                "smaller than the given one");
        this.transfer = transfer;
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
        if (worker == null) return;
        Request request = new Request(getName(), args, worker);
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
