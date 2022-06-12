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
 * Insert at command. Inserts new worker at the position as a collection index.
 */
public class InsertAt extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public InsertAt(Transfer transfer) {
        super("insert_at", "add a new worker to a given position");
        this.transfer = transfer;
    }

    /**
     * Adds new worker to a given position. If the position is incorrect execution will be stopped.
     * During work, it makes a request to enter data about the worker
     * through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param args the index to insert at
     * @see AddRequest
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> result = new ArrayList<>();
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
