package commands.concrete;

import collection.entity.Worker;
import commands.AddRequest;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;

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
    public void action(String args) {
        int index;
        try {
            index = Integer.parseInt(args);
            if (index < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Invalid index");
            return;
        }
        Worker worker = new AddRequest().requestWorker();
        if (worker == null) return;
        Request request = new Request(getName(), Integer.toString(index), worker);
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
