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
 */
public class Add extends Command {

    public Add(Transfer transfer) {
        super("add", transfer);
    }

    /**
     * Adds new worker to the given collection. Also assigns ID to a new worker.
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