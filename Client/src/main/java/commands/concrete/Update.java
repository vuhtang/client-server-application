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

    public Update(Transfer transfer) {
        super("update", transfer);
    }

    /**
     * Updates data about the worker with the given ID. If ID is incorrect execution will be stopped.
     * Firstly removes worker with this ID, then adds new worker and doesn't assign new ID to this worker.
     *
     * @return execution result list
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
        return defaultAction(new Request(getName(), Integer.toString(id), worker), result);
    }
}