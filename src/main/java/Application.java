import collection.entity.Worker;
import collection.WorkersCollection;
import commands.CommandExecutor;
import exceptions.InvalidInputException;
import parser.WorkersReader;

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
        if (args.length != 1){
            System.out.println("Old collection will be used");
            try{
                List<Worker> list = WorkersReader.readWorkers(filePath);
            } catch (InvalidInputException | IOException e) {
                System.out.println(e.getMessage() + "\nsomething went wrong(");
                return;
            }
        } else{
            filePath = args[0];
            try{
                List<Worker> list = WorkersReader.readWorkers(filePath);
            } catch (InvalidInputException | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Old collection will be used");
                filePath = "./src/main/resources/data_files/saved_data.csv";
                try{
                    List<Worker> list = WorkersReader.readWorkers(filePath);
                } catch (InvalidInputException | IOException e1) {
                    System.out.println(e.getMessage() + "\nsomething went wrong(");
                    return;
                }
            }
        }

        WorkersCollection workers = new WorkersCollection(filePath);
        try {
            workers.addAll(WorkersReader.readWorkers(filePath));
        } catch (IOException | InvalidInputException e) {
            System.out.println(e.getMessage() + "\nsomething went wrong(" + filePath);
            return;
        }

        String line;
        Scanner sc = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor(workers);
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
