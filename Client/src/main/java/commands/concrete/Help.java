package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
