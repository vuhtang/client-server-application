package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;
import exceptions.InvalidInputException;
import repository.SqlManager;
import transferring.Token;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends Command {

    private final WorkerColManager colManager;

    public LogIn(WorkerColManager colManager){
        super("logIn", "check user in data base");
        this.colManager = colManager;
    }

    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        SqlManager sqlManager = colManager.getSqlManager();
        boolean f;
        try {
            f = sqlManager.checkUser(token);
        } catch (SQLException | InvalidInputException e) {
            response.add(e.getMessage());
            return response;
        }
        if (f) response.add("ye");
        else response.add("false");
        return response;
    }
}
