package commands.concrete;

import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.Collection;

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
    public void action(String args) {
        Request request = new Request(getName(), args);
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
