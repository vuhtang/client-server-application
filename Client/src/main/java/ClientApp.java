import commands.CommandExecutor;
import exceptions.InvalidInputException;
import transferring.Token;
import transferring.Transfer;

import java.util.Scanner;

/**
 * Contains a main method that starts the program.
 */
public class ClientApp {
    private static final int PORT = 1014;
    private static final String HOST = "localhost";

    /**
     * Starts the application with args, which are passed further to a method 'run'.
     *
     * @param args path to file with collection
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login:");
        String userLogin = scanner.nextLine().trim();
        System.out.println("Enter password:");
        String userPassword = scanner.nextLine().trim();
        Token token = new Token(userLogin, userPassword);
        Transfer transfer = new Transfer(HOST, PORT, token);
        CommandExecutor executor = new CommandExecutor(transfer);
        String line;
        System.out.println("Enter \"help\" to see the list of available commands");
        while (true) {
            if (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
                try {
                    executor.executeCommand(line);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            } else break;
        }
    }
}
