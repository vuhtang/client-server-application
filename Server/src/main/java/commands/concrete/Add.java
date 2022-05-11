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
 * Add command. It adds new worker to the given collection,
 * worker data is entered in the console sequentially.
 */
public class Add extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Add(WorkerColManager colManager) {
        super("add", "add a new element to the collection");
        this.colManager = colManager;
    }

    /**
     * Adds new worker to the given collection. During work, it makes a request to enter
     * data about the worker through the console using AddRequest. Also assigns ID to a new worker.
     *
     * @param args an empty line, as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        if (worker == null) {
            response.add("Unable to add worker");
            return response;
        }
        SqlManager sqlManager = colManager.getSqlManager();
        try {
            int id = sqlManager.addWorkerWithoutIdToDB(worker, token);
            worker.setId(id);
            colManager.addWorker(worker);
        } catch (SQLException e) {
            response.add(e.getMessage());
            return response;
        }
        response.add("Worker added successfully!");
        return response;
    }
}