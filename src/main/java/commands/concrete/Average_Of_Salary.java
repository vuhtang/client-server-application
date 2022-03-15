package commands.concrete;

import collection.entity.Worker;
import collection.WorkersCollection;
import commands.Command;

/**
 * Average of salary command. It computes and prints the average salary of workers in the collection.
 */
public class Average_Of_Salary extends Command {
    /**
     * Initialised the name and the description of the new command.
     */
    public Average_Of_Salary() {
        super("average_of_salary",
                "get the average value of the salary for all items in the collection");
    }

    /**
     * Computes and prints the average salary of workers in the collection.
     *
     * @param workers the collection of workers to compute value of
     * @param args    an empty line, as an imperfection of the program model
     */
    @Override
    public void action(WorkersCollection workers, String args) {
        System.out.println("\nAverage of salary: " +
                workers.stream().mapToLong(Worker::getSalary).average().getAsDouble() + "\n");
    }
}
