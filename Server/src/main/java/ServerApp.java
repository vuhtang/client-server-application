import collection.WorkerColManager;
import collection.WorkerCollection;
import commands.CommandExecutor;
import exceptions.InvalidInputException;
import repository.SqlManager;
import server.Server;
import transferring.Token;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

//ввести в консоль:
//ssh -L 1077:pg:5432 s{isuNumber}@se.ifmo.ru -p 2222
public class ServerApp {
    public static final int PORT = 1014;
    private static final Logger logger = Logger.getLogger(ServerApp.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("./logging.properties"));
        } catch (IOException e) {
            System.out.println("Could not setup logger configuration: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login: ");
        String login = scanner.nextLine().trim();
        System.out.println("Enter password: ");
        String pwd = scanner.nextLine().trim();
        SqlManager sqlManager;
        try {
            sqlManager = new SqlManager(login, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sqlManager.closeConnection();
                System.out.println("bye");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
        WorkerColManager collectionManager = new WorkerColManager(new WorkerCollection(), sqlManager);
        try {
            collectionManager.addAllWorkers(sqlManager.getWorkersFromDB());
        } catch (SQLException | InvalidInputException e) {
            System.out.println(e.getMessage());
            System.out.println("Further work of the program is impossible");
            return;
        }
        logger.info("Collection loaded");
        try {
            Server server = new Server(PORT, collectionManager, logger);
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not connect to port: " + PORT, e);
        }
        Token adminToken = new Token(login, pwd);
        CommandExecutor commandExecutor = new CommandExecutor(collectionManager);
        String line;
        while (true) {
            if (scanner.hasNext()) {
                line = scanner.nextLine().trim();
                try {
                    List<String> response = commandExecutor.executeCommand(line, null, adminToken);
                    if (response != null) response.forEach(System.out::println);
                } catch (InvalidInputException e) {
                    logger.log(Level.SEVERE, "Command \"" + line + "\" could not be executed: "
                            + e.getMessage(), e);
                }
            } else break;
        }
    }
}