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

    public InsertAt(WorkerColManager colManager) {
        super("insert_at", "add a new worker to a given position");
        this.colManager = colManager;
    }

    /**
     * Adds new worker to a given position. If the position is incorrect execution will be stopped.
     * Also assigns ID to a new worker.
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
            list = list.stream().filter(worker1 -> worker1.getOwner().equals(token.getUserName())).toList();
            int newId = sqlManager.addWorkerWithoutIdToDB(worker,token);
            worker.setId(newId);
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
