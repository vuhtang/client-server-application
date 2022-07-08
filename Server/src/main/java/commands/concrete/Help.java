package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import commands.CommandExecutor;
import transferring.Token;

import java.util.List;

/**
 * Help command. Displays help on available commands.
 */
public class Help extends Command {

    private final CommandExecutor executor;

    public Help(CommandExecutor executor) {
        super("help", "display help on available commands");
        this.executor = executor;
    }

    /**
     * Displays all commands and their descriptions.
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        return executor.getCommandsDescription();
    }
}