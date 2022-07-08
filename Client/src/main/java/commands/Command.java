package commands;

import collection.entity.Worker;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.List;

/**
 * Abstract class from which all commands are extended. It has an abstract method "action" as
 * a specific action of each concrete command.
 */
public abstract class Command {
    /**
     * The command name.
     */
    private final String name;
    /**
     * Entity that implements communication with the server.
     */
    protected final Transfer transfer;

    /**
     * Initialized the name and the description of the new command.
     *
     * @param name the command name
     */
    public Command(String name, Transfer transfer) {
        this.name = name;
        this.transfer = transfer;
    }

    public abstract List<String> action(String args, Worker worker);

    /**
     * Default action that every self-respecting command performs.
     *
     * @param request request to execute
     * @param result  execution result list
     * @return the same execution result list
     */
    public List<String> defaultAction(Request request, List<String> result) {
        try {
            result.addAll(transfer.transfer(request));
        } catch (IOException e) {
            result.add("Input/output exception");
        } catch (ClassNotFoundException e) {
            result.add("Object came to us broken");
        }
        return result;
    }

    public String getName() {
        return name;
    }
}
