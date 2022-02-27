import Collection.WorkersCollection;
import Commands.CommandExecutor;
import Exceptions.InvalidInputFormat;
import ParserCSV.WorkersReader;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    private  String filePath = "./data_test.csv";

    public static void main(String[] args) {
        Application app = new Application();
        app.run(args);
    }

    private void run(String[] args){
        if (args.length != 1){
            System.out.println("Old coollection will be used");
        } else filePath = args[0];

        WorkersCollection workers = new WorkersCollection(filePath);
        try{
            workers.addAll(WorkersReader.readWorkers(filePath));
        } catch (InvalidInputFormat | IOException e) {
            System.out.println(e.getMessage());
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
            } catch (InvalidInputFormat e) {
                System.out.println(e.getMessage());
                System.out.println("Enter \"help\" to see the list of available commands");
            }
        }
    }
}
