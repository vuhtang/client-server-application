package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;
import transferring.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Info command. Displays type, initialization date and amount of elements of the collection.
 */
public class Info extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Info(WorkerColManager colManager) {
        super("info", "print information about the collection to standard output");
        this.colManager = colManager;
    }

    /**
     * Displays information about the given collection.
     *
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        return new ArrayList<>(colManager.getInfo());
    }
}
