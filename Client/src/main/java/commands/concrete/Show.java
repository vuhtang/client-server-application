package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Show command. Gets all collection elements with their characteristics.
 */
public class Show extends Command {

    public Show(Transfer transfer) {
        super("show", transfer);
    }

    /**
     * Gets all collection elements with values of all their fields.
     *
     * @return execution result list with workers
     */
    @Override
    public List<String> action(String args, Worker worker) {
        return defaultAction(new Request(getName(), args), new ArrayList<>());
    }
}
