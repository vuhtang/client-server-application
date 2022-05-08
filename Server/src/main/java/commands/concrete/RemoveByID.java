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
 * Remove by ID command. Removes a worker with given ID. Only one worker will be removed
 * since the ID is unique for each worker in the given collection.
 */
public class RemoveByID extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public RemoveByID(WorkerColManager colManager) {
        super("remove_by_id", "remove worker by id");
        this.colManager = colManager;
    }

    /**
     * Removes worker by given ID. If the ID is incorrect execution will be stopped.
     *
     * @param args the ID of worker to be removed
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        int id;
        try {
            id = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            response.add("Invalid id of the worker");
            return response;
        }
        worker = colManager.getWorkerByID(id);
        if (worker == null) {
            response.add("There is no worker with this id");
            return response;
        } else {
            SqlManager sqlManager = colManager.getSqlManager();
            try {
                if (sqlManager.removeWorkerFromDB(worker, token) > 0) {
                    colManager.removeWorkerById(id);
                    response.add("Worker removed successfully");
                } else response.add("Access denied");
            } catch (SQLException e) {
                response.add(e.getMessage());
                return response;
            }
        }
        return response;
    }
}
