import collection.WorkerColManager;
import collection.entity.Worker;
import collection.WorkerCollection;
import com.opencsv.exceptions.CsvException;
import commands.CommandExecutor;
import exceptions.InvalidInputException;
import parser.WorkerReader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Contains a main method that starts the program.
 */
public class Application {
    private  String filePath = "./src/main/resources/data_files/saved_data.csv";

    /**
     * Starts the application with args, which are passed further to a method 'run'.
     *
     * @param args path to file with collection
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run(args);
    }

    /**
     * Initialized a new collection from the file, if the path to it was given
     * as an argument when starting the application. Otherwise, the default collection will be used.
     * It also starts an endless loop of command execution, which ends after the exit command is entered.
     *
     * @param args path to file with collection
     */
    private void run(String[] args){
        boolean flag = false;
        if (args.length == 1){
            filePath = args[0];
            try{
                List<Worker> list = WorkerReader.readWorkers(filePath);
                flag = true;
            } catch (CsvException e) {
                System.out.println(e.getMessage() + "\nAn error in line " + e.getLineNumber());
            } catch (IOException e1) {
                System.out.println("The data could not be read from the file");
            }
        }
        if (!flag){
            System.out.println("Default collection will be used");
            filePath = "./src/main/resources/data_files/saved_data.csv";
        }
        WorkerCollection workers = new WorkerCollection(filePath);
        WorkerColManager workerColManager = new WorkerColManager(workers);
        try {
            workerColManager.addAllWorkers(WorkerReader.readWorkers(filePath));
        } catch (CsvException e) {
            System.out.println(e.getMessage() + "\nAn error in line " + e.getLineNumber());
            return;
        } catch (IOException e1) {
            System.out.println("The data could not be read from the file");
            return;
        }
        CommandExecutor executor = new CommandExecutor(workerColManager);
        String line;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \"help\" to see the list of available commands");
        while (true){
            line = sc.nextLine();
            if (line.trim().equals("exit")){
                break;
            }
            try {
                executor.executeCommand(line.trim());
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter \"help\" to see the list of available commands");
            }
        }
    }
}
