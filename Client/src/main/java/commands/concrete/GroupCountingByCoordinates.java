package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Group counting by coordinates command. Selects elements of collection with same coordinates
 * and displays received groups.
 */
public class GroupCountingByCoordinates extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public GroupCountingByCoordinates(Transfer transfer) {
        super("group_counting_by_coordinates",
                "group the elements of the collection by the value of the field coordinates," +
                        " display the number of elements in each group");
        this.transfer = transfer;
    }

    /**
     * Groups workers from collection into groups and writes them down in a lists as a values in Map,
     * keys to which are coordinates of workers in appropriating list.
     *
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> result = new ArrayList<>();
        Request request = new Request(getName(), args);
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
