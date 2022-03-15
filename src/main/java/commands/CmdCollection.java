package commands;


import commands.concrete.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * The collection of all available commands in this application. It has a private static set of commands
 * extended from an abstract class Command. This set is initialized statically.
 */
public class CmdCollection {
    /**
     * The set of available commands.
     */
    private static final Set<Command> commands;

    static {
        Command[] cmds = {new Help(), new Exit(), new Save(), new Info(), new Add(), new Show(),
                new Update(), new Remove_By_ID(), new Clear(), new Insert_At(), new Remove_At(),
                new Remove_Lower(), new Average_Of_Salary(), new Group_Counting_By_Coordinates(),
                new Filter_By_Position(), new Execute_Script()};
        commands = new HashSet<>(Arrays.asList(cmds));
    }

    /**
     * Gives access to the list of application commands without the ability to modify it
     * since it only returns a copy of it.
     *
     * @return the copy of set of commands
     */
    public static Set<Command> getCommands() {
        return new HashSet<>(commands);
    }

    /**
     * Returns a command from the set if it exists otherwise returns null
     *
     * @param cmdName the name of a command
     * @return command with the given name or null if there's no command with the given name
     */
    public static Command getCommand(String cmdName) {
        Optional<Command> cmd = commands.stream().filter(c -> c.getName().equals(cmdName)).findFirst();
        return cmd.orElse(null);
    }
}
