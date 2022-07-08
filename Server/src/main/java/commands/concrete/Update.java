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
 * Update command. Updates data about the worker with the given ID.
 */
public class Update extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    public Update(WorkerColManager colManager) {
        super("update", "update the value of the collection" +
                " element whose id is equal to the given one");
        this.colManager = colManager;
    }

    /**
     * Updates data about the worker with the given ID. If ID is incorrect execution will be stopped.
     * Firstly removes worker with this ID, then adds new worker and doesn't assign new ID to this worker.
     *
     * @param args the id of the updating worker
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
        if (worker == null) {
            response.add("Unable to update worker");
            return response;
        }
        if (!colManager.checkId(id)) {
            response.add("There is no worker with such id");
            return response;
        } else {
            try {
                SqlManager sqlManager = colManager.getSqlManager();
                worker.setId(id);
                if (sqlManager.removeWorkerFromDB(worker, token) > 0) {
                    sqlManager.addWorkerWithIdToDB(worker, token);
                    colManager.removeWorkerById(id);
                    colManager.addWorker(worker);
                    response.add("Worker updated successfully");
                } else response.add("Access denied");
            } catch (SQLException e) {
                response.add(e.getMessage());
                return response;
            }
        }
        return response;
    }
}