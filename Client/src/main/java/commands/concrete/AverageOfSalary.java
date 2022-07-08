package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Average of salary command. It computes and prints the average salary of workers in the collection.
 */
public class AverageOfSalary extends Command {

    public AverageOfSalary(Transfer transfer) {
        super("average_of_salary", transfer);
    }

    /**
     * Computes and returns the average salary of workers in the collection.
     *
     * @return execution result list with salary
     */
    @Override
    public List<String> action(String args, Worker worker) {
        return defaultAction(new Request(getName(), args), new ArrayList<>());
    }
}
