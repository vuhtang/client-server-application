package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clear command. It removes all workers from the collection.
 */
public class Clear extends Command {

    public Clear(Transfer transfer) {
        super("clear", transfer);
    }

    /**
     * Removes all workers from the given collection.
     *
     * @return execution result list
     */
    @Override
    public List<String> action(String args, Worker worker) {
        return defaultAction(new Request(getName(), args), new ArrayList<>());
    }
}
