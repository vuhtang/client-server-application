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
 * Remove lower command. Removes all workers lower than the given one. Workers are compared by salary.
 *
 * @see Worker#compareTo(Worker)
 */
public class RemoveLower extends Command {

    public RemoveLower(Transfer transfer) {
        super("remove_lower", transfer);
    }

    /**
     * Removes all workers lower than the given one.
     *
     * @return execution result list
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> result = new ArrayList<>();
        if (worker == null) return null;
        return defaultAction(new Request(getName(), args, worker), result);
    }
}
