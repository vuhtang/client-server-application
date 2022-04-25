import collection.WorkerColManager;
import collection.WorkerCollection;
import com.opencsv.exceptions.CsvException;
import commands.CommandExecutor;
import exceptions.InvalidInputException;
import parser.WorkerReader;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServerApp {
    public static final int PORT = 1013;
    private static final Logger logger = Logger.getLogger(ServerApp.class.getName());

    public static void main(String[] args) {
        File file = new File("Server/src/main/resources/saved_data.csv");
        String filePath = file.getAbsolutePath();
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("./logging.properties"));
        } catch (IOException e) {
            System.out.println("Could not setup logger configuration: " + e.getMessage());
        }
        WorkerCollection workers = new WorkerCollection(filePath);
        WorkerColManager workerColManager = new WorkerColManager(workers);
        try {
            workerColManager.addAllWorkers(WorkerReader.readWorkers(filePath));
        } catch (CsvException e) {
            logger.log(Level.SEVERE, e.getMessage() + "\nAn error in line " + e.getLineNumber(), e);
            return;
        } catch (IOException e1) {
            logger.log(Level.SEVERE, "The data could not be read from the file", e1);
            return;
        }
        logger.info("Collection loaded");
        logger.info("Waiting for connection...");

        try {
            Server server = new Server(PORT, workerColManager, logger);
            Thread thread = new Thread(server);
            thread.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not connect to port: " + PORT, e);
        }
        Scanner scanner = new Scanner(System.in);
        CommandExecutor commandExecutor = new CommandExecutor(workerColManager);
        String line = "";
        while (true) {
            if (scanner.hasNext()) line = scanner.nextLine().trim();
            try {
                List<String> response = commandExecutor.executeCommand(line, null);
                if (response != null) response.forEach(System.out::println);
            } catch (InvalidInputException e) {
                logger.log(Level.SEVERE, "Command \"" + line + "\" could not be executed: "
                        + e.getMessage(), e);
            }
        }
    }
}