import commands.CommandExecutor;
import exceptions.InvalidInputException;
import transferring.Transfer;

import java.util.Scanner;

/**
 * Contains a main method that starts the program.
 */
public class ClientApp {
    private static final int PORT = 1013;
    private static final String HOST = "localhost";

    /**
     * Starts the application with args, which are passed further to a method 'run'.
     *
     * @param args path to file with collection
     */
    public static void main(String[] args) {
        Transfer transfer = new Transfer(HOST, PORT);
        Scanner scanner = new Scanner(System.in);
        CommandExecutor executor = new CommandExecutor(transfer);
        String line = "";
        System.out.println("Enter \"help\" to see the list of available commands");
        while (true) {
            if (scanner.hasNextLine()) line = scanner.nextLine().trim();
            try {
                executor.executeCommand(line);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
