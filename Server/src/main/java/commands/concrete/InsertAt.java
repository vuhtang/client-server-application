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
 * Insert at command. Inserts new worker at the position as a collection index.
 */
public class InsertAt extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public InsertAt(WorkerColManager colManager) {
        super("insert_at", "add a new worker to a given position");
        this.colManager = colManager;
    }

    /**
     * Adds new worker to a given position. If the position is incorrect execution will be stopped.
     * During work, it makes a request to enter data about the worker
     * through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param args the index to insert at
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        int index;
        try {
            index = Integer.parseInt(args);
            if (index < 0 || index > colManager.getSize() - 1) {
                response.add("Invalid index (available indexes: [0, " + (colManager.getSize() - 1) + "])");
                return response;
            }
        } catch (NumberFormatException e) {
            response.add("Invalid index");
            return response;
        }
        if (worker == null) {
            response.add("Unable to add worker(it's null)");
            return response;
        }
        SqlManager sqlManager = colManager.getSqlManager();
        List<Worker> list = new ArrayList<>();
        for (int i = index; i < colManager.getSize(); i++) {
            list.add(colManager.getWorkerByIndex(i));
        }
        try {
            for (Worker w: list) {
                sqlManager.removeWorkerFromDB(w, token);
            }
            sqlManager.addWorkerWithoutIdToDB(worker,token);
            for (Worker w: list) {
                sqlManager.addWorkerWithIdToDB(w, token);
            }
            colManager.insertAt(index, worker);
            response.add("Worker added successfully");
        } catch (SQLException e) {
            response.add(e.getMessage());
            return response;
        }
        return response;
    }
}
