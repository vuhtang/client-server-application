package commands.concrete;

import commands.Command;
import transferring.Token;
import transferring.Transfer;

import java.util.Scanner;

public class LogIn extends Command {

    private final Transfer transfer;

    public LogIn(Transfer transfer) {
        super("log_in", "application login");
        this.transfer = transfer;
    }

    @Override
    public void action(String args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login:");
        String userName = scanner.nextLine().trim();
        System.out.println("Enter password:");
        String userPassword = scanner.nextLine().trim();
        Token token = new Token(userName, userPassword);
        transfer.setToken(token);
        System.out.println("You are logged in");
    }
}
