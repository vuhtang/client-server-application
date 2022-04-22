package commands.concrete;

import collection.entity.Position;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
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
    public void action(String args) {
        Position position;
        try {
            position = Position.valueOf(args.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            System.out.println("No such position exists. Existing positions: " +
                    "LABORER, HEAD_OF_DIVISION, MANAGER_OF_CLEANING");
            return;
        }
        Request request = new Request(getName(), position.toString());
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
