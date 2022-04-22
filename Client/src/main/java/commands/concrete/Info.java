package commands.concrete;

import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;

/**
 * Info command. Displays type, initialization date and amount of elements of the collection.
 */
public class Info extends Command {
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Info(Transfer transfer) {
        super("info", "print information about the collection to standard output");
        this.transfer = transfer;
    }

    /**
     * Displays information about the given collection.
     *
     * @param args an empty string as an imperfection of the program model
     */
    @Override
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
