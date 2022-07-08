package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Token;

import java.util.List;

/**
 * Exit command. In fact, it is just a label for terminating the command execution loop.
 */
public class Exit extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Exit() {
        super("exit", "terminate program (without saving to file)");
    }

    @Override
    public List<String> action(String args, Worker worker, Token token) {
        System.exit(0);
        return null;
    }
}
