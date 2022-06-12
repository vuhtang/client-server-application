package commands.concrete;

import collection.entity.Worker;
import commands.AddRequest;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> action(String args, Worker worker) {
        List<String> result = new ArrayList<>();
        if (worker == null) return null;
        Request request = new Request(getName(), args, worker);
        try {
            result.addAll(transfer.transfer(request));
        } catch (IOException e) {
            result.add("Input/output exception");
        } catch (ClassNotFoundException e) {
            result.add("Object came to us broken");
        }
        return result;
    }
}