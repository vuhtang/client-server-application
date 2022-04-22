package commands.concrete;

import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;

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
    public void action(String args) {
        Request request = new Request(getName(), args);
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
