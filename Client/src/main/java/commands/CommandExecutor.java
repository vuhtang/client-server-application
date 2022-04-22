package commands;

import commands.concrete.*;
import exceptions.InvalidInputException;
import transferring.Transfer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


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
     * Initialized command executor and commands with the given collection manager.
     */
    public CommandExecutor(Transfer transfer) {
        this.commands = new HashSet<>(Arrays.asList(
                new Help(transfer), new Info(transfer), new Add(transfer),
                new Show(transfer), new Update(transfer), new RemoveByID(transfer),
                new Clear(transfer), new InsertAt(transfer), new RemoveAt(transfer),
                new RemoveLower(transfer), new AverageOfSalary(transfer),
                new GroupCountingByCoordinates(transfer), new FilterByPosition(transfer),
                new ExecuteScript(transfer), new Exit()
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
     * @param value the name and the argument separated by space
     * @throws InvalidInputException if a command with the given name doesn't exist
     */
    public void executeCommand(String value) throws InvalidInputException {
        String[] valueParts = value.trim().split(" +");
        Command cmd = getCommand(valueParts[0]);
        if (cmd == null) throw new InvalidInputException("Command \"" + valueParts[0] + "\" does not exist");
        if (valueParts.length == 1) {
            cmd.action("");
        } else if (valueParts.length == 2) {
            cmd.action(valueParts[1]);
        } else throw new InvalidInputException("Incorrect number of arguments, only one argument is expected");
    }
}
