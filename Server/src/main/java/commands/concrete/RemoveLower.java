package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;
import exceptions.InvalidInputException;
import repository.SqlManager;
import transferring.Token;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Remove lower command. Removes all workers lower than the given one. Workers are compared by salary.
 *
 * @see Worker#compareTo(Worker)
 */
public class RemoveLower extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    public RemoveLower(WorkerColManager colManager) {
        super("remove_lower", "remove from the collection all elements " +
                "smaller than the given one");
        this.colManager = colManager;
    }

    /**
     * Removes all workers lower than the given one.
     *
     * @param args an empty string as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        SqlManager sqlManager = colManager.getSqlManager();
        try {
            List<Worker> list = colManager.getAllLower(worker);
            for (Worker w: list) {
                sqlManager.removeWorkerFromDB(w, token);
            }
            colManager.clear();
            colManager.addAllWorkers(sqlManager.getWorkersFromDB());
            response.add("Workers removed successfully");
        } catch (SQLException | InvalidInputException e) {
            response.add(e.getMessage());
            return response;
        }
        return response;
    }
}