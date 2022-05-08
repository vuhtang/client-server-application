package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;
import exceptions.InvalidInputException;
import repository.SqlManager;
import transferring.Token;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clear command. It removes all workers from the collection.
 */
public class Clear extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Clear(WorkerColManager colManager) {
        super("clear", "remove all workers from collection");
        this.colManager = colManager;
    }

    /**
     * Removes all workers from the given collection using removeAll method of super class.
     *
     * @param args an empty line, as an imperfection of the program model
     * @see java.util.ArrayList#removeAll(Collection)
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        SqlManager sqlManager = colManager.getSqlManager();
        try {
            if (sqlManager.clear(token) > 0) {
                colManager.clear();
                colManager.addAllWorkers(sqlManager.getWorkersFromDB());
            } else {
                response.add("There were no workers to delete");
                return response;
            }
        } catch (SQLException | InvalidInputException e) {
            response.add(e.getMessage());
            return response;
        }
        response.add("Collection cleared successfully");
        return response;
    }
}
