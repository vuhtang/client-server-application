package commands.concrete;

import collection.entity.Worker;
import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends Command {

    private final Transfer transfer;

    public LogIn(Transfer transfer) {
        super("logIn", "application login");
        this.transfer = transfer;
    }

    @Override
    public List<String> action(String args, Worker worker) {
        List<String> result = new ArrayList<>();
        Request request = new Request(getName(),  args);
        try {
            List<String> list = transfer.transfer(request);
            if (list == null) result.add("Server is not available now");
            else result.addAll(list);
        } catch (IOException e) {
            result.add("Input/output exception");
        } catch (ClassNotFoundException e) {
            result.add("Object came to us broken");
        }
        return result;
    }
}
