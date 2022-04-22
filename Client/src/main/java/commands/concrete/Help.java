package commands.concrete;

import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;

/**
 * Help command. Displays help on available commands.
 */
public class Help extends Command {
    private final Transfer transfer;

    /**
     * Initialised the name and the description of the new command.
     */
    public Help(Transfer transfer) {
        super("help", "display help on available commands");
        this.transfer = transfer;
    }

    /**
     * Displays all commands and their descriptions.
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
