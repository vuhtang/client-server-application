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

    public RemoveByID(Transfer transfer) {
        super("remove_by_id", transfer);
    }

    /**
     * Removes worker by given ID. If the ID is incorrect execution will be stopped.
     *
     * @return execution result list
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
        return defaultAction(new Request(getName(), Integer.toString(id)), result);
    }
}
