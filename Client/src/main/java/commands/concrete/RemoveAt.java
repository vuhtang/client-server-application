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

    public RemoveAt(Transfer transfer) {
        super("remove_at", transfer);
    }

    /**
     * Removes a worker at a given position. If the position is incorrect execution will be stopped.
     *
     * @return execution result list
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
        return defaultAction(new Request(getName(), Integer.toString(index)), result);
    }
}
