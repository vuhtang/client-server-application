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

/**
 * Command for registration. Registers new user and writes him to the database.
 */
public class Register extends Command {

    private final WorkerColManager colManager;

    public Register(WorkerColManager colManager) {
        super("register", "register user in data base");
        this.colManager = colManager;
    }

    @Override
    public List<String> action(String args, Worker worker, Token token) {
        List<String> response = new ArrayList<>();
        String[] args0 = args.split(",");
        String userLogin = args0[0];
        String userPassword = args0[1];
        SqlManager sqlManager = colManager.getSqlManager();
        try {
            sqlManager.addUser(new Token(userLogin, userPassword));
        } catch (SQLException e) {
            response.add(e.getMessage());
            return response;
        }
        response.add("User registered successfully");
        return response;
    }
}
