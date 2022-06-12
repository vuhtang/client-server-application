package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Remove by ID command. Removes a worker with given ID. Only one worker will be removed
 * since the ID is unique for each worker in the given collection.
 */
public class RemoveByID extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveByID(Transfer transfer) {
        super("remove_by_id", "remove worker by id");
        this.transfer = transfer;
    }

    /**
     * Removes worker by given ID. If the ID is incorrect execution will be stopped.
     *
     * @param args the ID of worker to be removed
     */
    @Override
    public List<String> action(String args, Worker worker) {
        int id;
        List<String> result = new ArrayList<>();
        try {
            id = Integer.parseInt(args);
            if (id <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            result.add("It's not an integer");
            return result;
        }
        Request request = new Request(getName(), Integer.toString(id));
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
