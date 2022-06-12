package commands;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.concrete.*;
import exceptions.InvalidInputException;
import transferring.Token;

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
     * Initialized command executor and commands with the given collection manager.
     *
     * @param colManager the collection manager for commands
     */
    public CommandExecutor(WorkerColManager colManager) {
        this.commands = new HashSet<>(Arrays.asList(
                new Help(this), new Exit(), new Info(colManager), new Add(colManager),
                new Show(colManager), new Update(colManager), new RemoveByID(colManager),
                new Clear(colManager), new InsertAt(colManager), new RemoveAt(colManager),
                new RemoveLower(colManager), new AverageOfSalary(colManager),
                new GroupCountingByCoordinates(colManager), new FilterByPosition(colManager),
                new LogIn(colManager), new Register(colManager)
        ));
    }

    public List<String> getCommandsDescription() {
        return commands.stream().map(command -> "\"" + command.getName() + "\" --- "
                + command.getDescription()).toList();
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
    public List<String> executeCommand(String value, Worker worker, Token token) throws InvalidInputException {
        List<String> response = new ArrayList<>();
        String[] valueParts = value.trim().split(" +");
        Command cmd = getCommand(valueParts[0]);
        if (cmd == null) throw new InvalidInputException("Command \"" + valueParts[0] + "\" does not exist");
        if (valueParts.length == 1) {
            response.addAll(cmd.action("", worker, token));
            return response;
        } else if (valueParts.length == 2) {
            response.addAll(cmd.action(valueParts[1], worker, token));
            return response;
        } else throw new InvalidInputException("Incorrect number of arguments, only one argument is expected");
    }
}
