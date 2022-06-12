package commands.concrete;

import collection.entity.Position;
import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Filter by position command. Selects workers from a collection
 * with a given position and displays the resulting list.
 */
public class FilterByPosition extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public FilterByPosition(Transfer transfer) {
        super("filter_by_position",
                "display elements whose position field value is equal to the given one");
        this.transfer = transfer;
    }

    /**
     * Selects workers from a collection with a given position. If such position
     * does not exist, then the execution will be stopped. Then prints a list of workers with
     * this position.
     *
     * @param args the position to filter by
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> result = new ArrayList<>();
        Position position;
        try {
            position = Position.valueOf(args.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            result.add("No such position exists. Existing positions: " +
                    "LABORER, HEAD_OF_DIVISION, MANAGER_OF_CLEANING");
            return result;
        }
        Request request = new Request(getName(), position.toString());
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
