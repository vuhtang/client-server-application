package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Average of salary command. It computes and prints the average salary of workers in the collection.
 */
public class AverageOfSalary extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public AverageOfSalary(WorkerColManager colManager) {
        super("average_of_salary",
                "get the average value of the salary for all items in the collection");
        this.colManager = colManager;
    }

    /**
     * Computes and prints the average salary of workers in the collection.
     *
     * @param args an empty line, as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker) {
        List<String> response = new ArrayList<>();
        response.add("Average of salary: " + colManager.averageOfSalary());
        return response;
    }
}
