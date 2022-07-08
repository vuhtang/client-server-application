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
 * Insert At command. Inserts new worker at the position as a collection index.
 */
public class InsertAt extends Command {

    public InsertAt(Transfer transfer) {
        super("insert_at", transfer);
    }

    /**
     * Adds new worker to a given position. If the position is incorrect execution will be stopped.
     *
     * @return execution result list
     */
    @Override
    public List<String> action(String args, Worker worker) {
        return defaultAction(new Request(getName(), args, worker), new ArrayList<>());
    }
}
