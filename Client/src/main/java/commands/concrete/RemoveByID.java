package commands.concrete;

import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;

/**
 * Remove by ID command. Removes a worker with given ID. Only one worker will be removed
 * since the ID is unique for each worker in the given collection.
 */
public class RemoveByID extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveByID(Transfer transfer) {
        super("remove_by_id", "remove worker by id");
        this.transfer = transfer;
    }

    /**
     * Removes worker by given ID. If the ID is incorrect execution will be stopped.
     *
     * @param args the ID of worker to be removed
     */
    public void action(String args) {
        int id;
        try {
            id = Integer.parseInt(args);
            if (id <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("It's not an integer");
            return;
        }
        Request request = new Request(getName(), Integer.toString(id));
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
