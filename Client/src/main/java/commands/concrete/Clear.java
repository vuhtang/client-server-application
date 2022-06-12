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
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Clear(Transfer transfer) {
        super("clear", "remove all workers from collection");
        this.transfer = transfer;
    }

    /**
     * Removes all workers from the given collection using removeAll method of super class.
     *
     * @param args an empty line, as an imperfection of the program model
     * @see java.util.ArrayList#removeAll(Collection)
     */
    @Override
    public List<String> action(String args, Worker worker) {
        Request request = new Request(getName(), args);
        List<String> result = new ArrayList<>();
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
