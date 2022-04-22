package commands.concrete;

import collection.entity.Worker;
import commands.AddRequest;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;

/**
 * Add command. It adds new worker to the given collection,
 * worker data is entered in the console sequentially.
 */
public class Add extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Add(Transfer transfer) {
        super("add", "add a new element to the collection");
        this.transfer = transfer;
    }

    /**
     * Adds new worker to the given collection. During work, it makes a request to enter
     * data about the worker through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param args an empty line, as an imperfection of the program model
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