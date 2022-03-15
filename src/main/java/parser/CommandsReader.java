package parser;

import collection.WorkersCollection;
import commands.CommandExecutor;
import exceptions.InvalidInputException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * An entity that reads and executes commands from file for concrete collection.
 */
public class CommandsReader {
    /**
     * The collection to work with.
     */
    private WorkersCollection workers;

    /**
     * Initialized a new reader with the given collection.
     *
     * @param workers the collection to work with
     */
    public CommandsReader(WorkersCollection workers) {
        this.workers = workers;
    }

    /**
     * Executes commands from file.
     *
     * @param filePath the path to file with commands
     * @throws IOException           if an input exception occur
     * @throws InvalidInputException if a given file is empty
     */
    public void executeCommands(String filePath) throws IOException, InvalidInputException {
        CommandExecutor executor = new CommandExecutor(workers);
        for (String command : readCommands(filePath)) {
            executor.executeCommand(command);
        }
    }

    /**
     * Reads commands from the given file.
     *
     * @param filePath the path to file
     * @return the list of commands name
     * @throws IOException           if an input exception occur
     * @throws InvalidInputException if a given file is empty
     */
    private List<String> readCommands(String filePath) throws
            IOException, InvalidInputException {
        Path path = Paths.get(filePath);
        try {
            Scanner scanner = new Scanner(path);
        } catch (IOException e) {
            throw new IOException("No such file or directory");
        }
        Scanner scanner = new Scanner(path);
        List<String> list = new ArrayList<>();
        if (!scanner.hasNext()) throw new InvalidInputException("The input file is empty");
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
        return list;
    }
}
