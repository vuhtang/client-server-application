import gui.LoginFrame;
import transferring.Transfer;

import javax.swing.*;
import java.awt.*;

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
        EventQueue.invokeLater(ClientApp::run);
    }

    private static void run() {
        Transfer transfer = new Transfer(HOST, PORT, null);
        JFrame frame = new LoginFrame(transfer);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Welcome!");
        frame.setVisible(true);
    }
}
