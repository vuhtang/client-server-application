package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Update command. Updates data about the worker with the given ID.
 */
public class Update extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Update(Transfer transfer) {
        super("update", "update the value of the collection" +
                " element whose id is equal to the given one");
        this.transfer = transfer;
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
        int id;
        List<String> result = new ArrayList<>();
        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            result.add("It's not an integer");
            return result;
        }
        Request request = new Request(getName(), Integer.toString(id), worker);
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