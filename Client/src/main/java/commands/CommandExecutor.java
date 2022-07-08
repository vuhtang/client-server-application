package commands;

import collection.entity.Worker;
import commands.concrete.*;
import exceptions.InvalidInputException;
import transferring.Transfer;

import java.util.*;


/**
 * An entity executing commands for one specific collection that
 * is passed to it in the constructor.
 */
public class CommandExecutor {
    /**
     * Set of all available commends.
     */
    private final Set<Command> commands;
    /**
     * Entity that implements communication with the server.
     */
    private final Transfer transfer;

    /**
     * Initialized command executor and commands with the given collection manager.
     */
    public CommandExecutor(Transfer transfer) {
        this.transfer = transfer;
        this.commands = new HashSet<>(Arrays.asList(
                new Info(transfer), new Add(transfer),
                new Show(transfer), new Update(transfer), new RemoveByID(transfer),
                new Clear(transfer), new InsertAt(transfer), new RemoveAt(transfer),
                new RemoveLower(transfer), new AverageOfSalary(transfer),
                new Register(transfer),
                new LogIn(transfer)
        ));
    }

    public Set<Command> getCommands() {
        return new HashSet<>(commands);
    }

    public Command getCommand(String cmdName) {
        Optional<Command> cmd = commands.stream().filter(c -> c.getName().equals(cmdName)).findFirst();
        return cmd.orElse(null);
    }

    /**
     * Executes command for the collection of this executor. Method looks for a command with such name.
     * If such a command doesn't exist execution will be stopped. Otherwise, command will be executed
     * with argument following its name.
     *
     * @param cmd the name and the argument separated by space
     * @throws InvalidInputException if a command with the given name doesn't exist
     */
    public List<String> executeCommand(String cmd) throws InvalidInputException {
        String[] valueParts = cmd.trim().split(" +");
        Command command = getCommand(valueParts[0]);
        if (command == null) throw new InvalidInputException("Command \"" + valueParts[0] + "\" does not exist");
        if (valueParts.length == 1) {
            return command.action("", null);
        } else if (valueParts.length == 2) {
            return command.action(valueParts[1], null);
        } else throw new InvalidInputException("Incorrect number of arguments, only one argument is expected");
    }

    public List<String> executeCommand(String cmd, Worker worker) throws InvalidInputException {
        String[] valueParts = cmd.trim().split(" +");
        Command command = getCommand(valueParts[0]);
        if (command == null) throw new InvalidInputException("Command \"" + valueParts[0] + "\" does not exist");
        if (valueParts.length == 1) {
            return command.action("", worker);
        } else if (valueParts.length == 2) {
            return command.action(valueParts[1], worker);
        } else throw new InvalidInputException("Incorrect number of arguments, only one argument is expected");
    }

    public Transfer getTransfer() {
        return transfer;
    }
}
