package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Show command. Displays all collection elements with their characteristics.
 */
public class Show extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Show(Transfer transfer) {
        super("show", "print all elements of collection to standard output");
        this.transfer = transfer;
    }

    /**
     * Shows all collection elements with values of all their fields.
     *
     * @param args an empty string as an imperfection of the program model
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
