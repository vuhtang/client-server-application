package commands;

import collection.WorkersCollection;
import exceptions.InvalidInputException;

/**
 * An entity executing commands for one specific collection that
 * is passed to it in the constructor.
 */
public class CommandExecutor {
    /**
     * The collection of workers to work with.
     */
    private WorkersCollection workers;

    /**
     * Initialized the new executor for the given collection of workers.
     *
     * @param workers the collection to work with
     */
    public CommandExecutor(WorkersCollection workers) {
        this.workers = workers;
    }

    /**
     * Executes command for the collection of this executor. Method looks for a command with such name.
     * If such a command doesn't exist execution will be stopped. Otherwise, command will be executed
     * with argument following its name.
     *
     * @param value the name and the argument separated by space
     * @throws InvalidInputException if a command with the given name doesn't exist
     */
    public void executeCommand(String value) throws InvalidInputException {
        String[] valueParts = value.trim().split(" ");
        Command cmd = CmdCollection.getCommand(valueParts[0]);
        if (cmd == null) throw new InvalidInputException("Command \"" + valueParts[0] + "\" does not exist");
        if (valueParts.length == 1) {
            cmd.action(workers, "");
        } else if (valueParts.length == 2) {
            cmd.action(workers, valueParts[1]);
        } else throw new InvalidInputException("Incorrect number of arguments, only one argument is expected");
    }
}
