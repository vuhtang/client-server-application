package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Info command. Displays type and initialization date.
 */
public class Info extends Command {

    public Info(Transfer transfer) {
        super("info", transfer);
    }

    /**
     * Displays information about the given collection.
     *
     * @return execution result list with info
     */
    @Override
    public List<String> action(String args, Worker worker) {
        return defaultAction(new Request(getName(), args), new ArrayList<>());
    }
}
