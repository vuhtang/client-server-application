package Parser;

import Collection.WorkersCollection;
import Commands.CommandExecutor;
import Exceptions.InvalidInputFormat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandsReader {
    private WorkersCollection workers;

    public CommandsReader(WorkersCollection workers) {
        this.workers = workers;
    }

    public void executeCommands(String filePath) throws IOException, InvalidInputFormat {
        CommandExecutor executor = new CommandExecutor(workers);
        for (String command: readCommands(filePath)){
            executor.executeCommand(command);
        }
    }
    private List<String> readCommands(String filePath) throws
            IOException, InvalidInputFormat{
        Path path = Paths.get(filePath);
        try {
            Scanner scanner = new Scanner(path);
        } catch (IOException e){
            throw new IOException("No such file or directory");
        }
        Scanner scanner = new Scanner(path);
        List<String> list = new ArrayList<>();
        if (!scanner.hasNext()) throw new InvalidInputFormat("The input file is empty");
        while (scanner.hasNext()){
            list.add(scanner.nextLine());
        }
        return list;
    }
}
