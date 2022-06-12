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
    private final Transfer transfer;

    /**
     * Initialised collection manager, the name and the description of the new command.
     */
    public AverageOfSalary(Transfer transfer) {
        super("average_of_salary",
                "get the average value of the salary for all items in the collection");
        this.transfer = transfer;
    }

    /**
     * Computes and prints the average salary of workers in the collection.
     *
     * @param args an empty line, as an imperfection of the program model
     */
    @Override
    public List<String> action(String args, Worker worker) {
        Request request = new Request(getName(), args);
        List<String> result = new ArrayList<>();
        try {
            result.addAll(transfer.transfer(request));
        } catch (IOException e) {
            result.add("Input/output exception");
        } catch (ClassNotFoundException e) {
            result.add("Object came to us broken");
        }
        return result;
    }
}
