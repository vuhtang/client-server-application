package parser;

import commands.CommandExecutor;
import exceptions.InvalidInputException;
import transferring.Transfer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * An entity that reads and executes commands from file for concrete collection.
 */
public class CommandReader {
    /**
     * The collection manager to work with.
     */
    private final Transfer transfer;

    /**
     * Initialized a new reader with the given collection.
     */
    public CommandReader(Transfer transfer) {
        this.transfer = transfer;
    }

    /**
     * Executes commands from file.
     *
     * @param filePath the path to file with commands
     * @throws IOException           if an input exception occurs
     * @throws InvalidInputException if a given file is empty
     */
    public List<String> executeCommands(String filePath) throws IOException, InvalidInputException {
        CommandExecutor executor = new CommandExecutor(transfer);
        List<String> result = new ArrayList<>();
        for (String command : readCommands(filePath)) {
            result.addAll(executor.executeCommand(command));
        }
        return result;
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
        Scanner scanner = new Scanner(Paths.get(filePath));
        List<String> list = new ArrayList<>();
        if (!scanner.hasNext()) throw new InvalidInputException("The input file is empty");
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
        return list;
    }
}
