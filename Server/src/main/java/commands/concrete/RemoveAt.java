package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;
import repository.SqlManager;
import transferring.Token;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Remove at command. Removes a worker at the position as a collection index.
 */
public class RemoveAt extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveAt(WorkerColManager colManager) {
        super("remove_at", "remove an element at a give position");
        this.colManager = colManager;
    }

    /**
     * Removes a worker at a given position. If the position is incorrect execution will be stopped.
     * Uses remove method of super class.
     *
     * @param args the index to remove at
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        int index;
        try {
            index = Integer.parseInt(args);
            if (index < 0 || index >= colManager.getSize()) {
                response.add("Invalid index");
                return response;
            }
        } catch (NumberFormatException e) {
            response.add("Invalid index");
            return response;
        }
        SqlManager sqlManager = colManager.getSqlManager();
        worker = colManager.getWorkerByIndex(index);
        try {
            if (sqlManager.removeWorkerFromDB(worker, token) > 0) {
                colManager.removeWorkerAt(index);
                response.add("Worker removed successfully!");
            } else response.add("Access denied");
        } catch (SQLException e) {
            response.add(e.getMessage());
            return response;
        }
        return response;
    }
}
