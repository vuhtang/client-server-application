package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;
import transferring.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Average of salary command. It computes and sends the average salary of workers in the collection.
 */
public class AverageOfSalary extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    public AverageOfSalary(WorkerColManager colManager) {
        super("average_of_salary",
                "get the average value of the salary for all items in the collection");
        this.colManager = colManager;
    }

    /**
     * Computes and sends the average salary of workers in the collection.
     */
    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        response.add(Double.toString(colManager.averageOfSalary()));
        return response;
    }
}
