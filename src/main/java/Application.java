import Collection.Entity.Worker;
import Collection.WorkersCollection;
import Commands.CommandExecutor;
import Exceptions.InvalidInputFormat;
import Parser.WorkersReader;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Application {
    private  String filePath = "./src/main/resources/data_files/saved_data.csv";

    public static void main(String[] args) {
        Application app = new Application();
        app.run(args);
    }

    private void run(String[] args){
        if (args.length != 1){
            System.out.println("Old collection will be used");
            try{
                List<Worker> list = WorkersReader.readWorkers(filePath);
            } catch (InvalidInputFormat | IOException e) {
                System.out.println(e.getMessage() + "\nsomething went wrong(");
                return;
            }
        } else{
            filePath = args[0];
            try{
                List<Worker> list = WorkersReader.readWorkers(filePath);
            } catch (InvalidInputFormat | IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Old collection will be used");
                filePath = "./src/main/resources/data_files/saved_data.csv";
                try{
                    List<Worker> list = WorkersReader.readWorkers(filePath);
                } catch (InvalidInputFormat | IOException e1) {
                    System.out.println(e.getMessage() + "\nsomething went wrong(");
                    return;
                }
            }
        }

        WorkersCollection workers = new WorkersCollection(filePath);
        try {
            workers.addAll(WorkersReader.readWorkers(filePath));
        } catch (IOException | InvalidInputFormat e) {
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
            } catch (InvalidInputFormat e) {
                System.out.println(e.getMessage());
                System.out.println("Enter \"help\" to see the list of available commands");
            }
        }
    }
}
