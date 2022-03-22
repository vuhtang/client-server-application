package commands;

import collection.WorkerColManager;
import commands.concrete.*;
import exceptions.InvalidInputException;

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
     *
     * @param colManager the collection manager for commands
     */
    public CommandExecutor(WorkerColManager colManager) {
        this.commands = new HashSet<>(Arrays.asList(
                new Help(), new Exit(), new Save(colManager), new Info(colManager), new Add(colManager),
                new Show(colManager), new Update(colManager), new Remove_By_ID(colManager),
                new Clear(colManager), new Insert_At(colManager), new Remove_At(colManager),
                new Remove_Lower(colManager), new Average_Of_Salary(colManager),
                new Group_Counting_By_Coordinates(colManager), new Filter_By_Position(colManager),
                new Execute_Script(colManager)
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
        String[] valueParts = value.trim().split(" ");
        Command cmd = getCommand(valueParts[0]);
        if (cmd == null) throw new InvalidInputException("Command \"" + valueParts[0] + "\" does not exist");
        if (valueParts.length == 1) {
            cmd.action("");
        } else if (valueParts.length == 2) {
            cmd.action(valueParts[1]);
        } else throw new InvalidInputException("Incorrect number of arguments, only one argument is expected");
    }
}
