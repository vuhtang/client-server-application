package commands.concrete;

import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;

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
    public void action(String args) {
        Request request = new Request(getName(), args);
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
