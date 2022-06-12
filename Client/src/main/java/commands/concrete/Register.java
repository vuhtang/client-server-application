package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.List;

public class Register extends Command {
    private final Transfer transfer;

    public Register(Transfer transfer) {
        super("register", "register user in data base");
        this.transfer = transfer;
    }
    @Override
    public List<String> action(String args, Worker worker) {
        Request request = new Request(getName(),  args);
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
