package commands.concrete;

import collection.WorkerColManager;
import collection.entity.Worker;
import commands.Command;
import repository.SqlManager;
import transferring.Token;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Register extends Command {

    private final WorkerColManager colManager;

    public Register(WorkerColManager colManager){
        super("register", "register user in data base");
        this.colManager = colManager;
    }

    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        SqlManager sqlManager = colManager.getSqlManager();
        try {
            sqlManager.addUser(token);
        } catch (SQLException e) {
            response.add(e.getMessage());
            return response;
        }
        response.add("User registered successfully");
        return response;
    }
}
