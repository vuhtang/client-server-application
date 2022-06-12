package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Remove at command. Removes a worker at the position as a collection index.
 */
public class RemoveAt extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveAt(Transfer transfer) {
        super("remove_at", "remove an element at a give position");
        this.transfer = transfer;
    }

    /**
     * Removes a worker at a given position. If the position is incorrect execution will be stopped.
     * Uses remove method of super class.
     *
     * @param args the index to remove at
     */
    @Override
    public List<String> action(String args, Worker worker) {
        int index;
        List<String> result = new ArrayList<>();
        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            result.add("Invalid index");
            return result;
        }
        Request request = new Request(getName(), Integer.toString(index));
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
