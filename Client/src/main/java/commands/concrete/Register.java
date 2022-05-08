package commands.concrete;

import commands.Command;
import transferring.Request;
import transferring.Transfer;

import java.io.IOException;
import java.util.Scanner;

public class Register extends Command {
    private final Transfer transfer;

    public Register(Transfer transfer) {
        super("register", "register user in data base");
        this.transfer = transfer;
    }
    @Override
    public void action(String args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login:");
        String userName = scanner.nextLine().trim();
        System.out.println("Enter password:");
        String userPassword = scanner.nextLine().trim();
        String argument = userName + " " + userPassword;
        Request request = new Request(getName(),  argument);
        try {
            transfer.transfer(request);
        } catch (IOException e) {
            System.out.println("Input/output exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Object came to us broken");
        }
    }
}
