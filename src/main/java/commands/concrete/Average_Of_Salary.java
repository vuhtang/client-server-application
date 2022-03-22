package commands.concrete;

import collection.WorkerColManager;
import commands.Command;

/**
 * Average of salary command. It computes and prints the average salary of workers in the collection.
 */
public class Average_Of_Salary extends Command {
    /**
     * Collection manager to work with.
     */
    private final WorkerColManager colManager;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public Average_Of_Salary(WorkerColManager colManager) {
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
    public void action(String args) {
        System.out.println("\nAverage of salary: " + colManager.averageOfSalary() + "\n");
    }
}
