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

    public Clear(WorkerColManager colManager) {
        super("clear", "remove all workers from collection");
        this.colManager = colManager;
    }

    /**
     * Removes all workers from the given collection.
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