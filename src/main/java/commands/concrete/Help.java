package commands.concrete;

import collection.WorkersCollection;
import commands.CmdCollection;
import commands.Command;

import java.util.Set;

/**
 * Help command. Displays help on available commands.
 */
public class Help extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Help() {
        super("help", "display help on available commands");
    }

    /**
     * Displays all commands and their descriptions from CmdCollection.
     *
     * @param workers the collection to work with
     * @param args an empty string as an imperfection of the program model
     * @see CmdCollection
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        System.out.println("\nThe list of available commands:");
        Set<Command> commands = CmdCollection.getCommands();
        for (Command command : commands) {
            System.out.println(" -" + command.getName()
                    + " //" + command.getDescription());
        }
        System.out.println();
    }
}
