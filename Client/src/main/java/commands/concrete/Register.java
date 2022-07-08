package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.List;

/**
 * Registers new user.
 */
public class Register extends Command {

    public Register(Transfer transfer) {
        super("register", transfer);
    }

    /**
     * Registers new user, if the registration is successful there will be a message
     *
     * @return execution result list
     */
    @Override
    public List<String> action(String args, Worker worker) {
        Request request = new Request(getName(), args);
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
        return null;
    }
}
