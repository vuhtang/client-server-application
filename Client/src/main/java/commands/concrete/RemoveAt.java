package commands.concrete;

import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;

/**
 * Remove at command. Removes a worker at the position as a collection index.
 */
public class RemoveAt extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveAt(Transfer transfer) {
        super("remove_at", "remove an element at a give position");
        this.transfer = transfer;
    }

    /**
     * Removes a worker at a given position. If the position is incorrect execution will be stopped.
     * Uses remove method of super class.
     *
     * @param args the index to remove at
     */
    @Override
    public void action(String args) {
        int index;
        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index");
            return;
        }
        Request request = new Request(getName(), Integer.toString(index));
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
