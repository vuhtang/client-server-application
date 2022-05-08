package server;

import collection.WorkerColManager;
import commands.CommandExecutor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
    private final CommandExecutor commandExecutor;
    private final ServerSocket serverSocket;

    private final Logger logger;
    private final WorkerColManager colManager;
    private final ExecutorService poolReader = Executors.newFixedThreadPool(9);

    public Server(int port, WorkerColManager colManager, Logger logger) throws IOException {
        this.colManager = colManager;
        this.logger = logger;
        this.serverSocket = new ServerSocket(port);
        this.commandExecutor = new CommandExecutor(colManager);
    }

    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                poolReader.execute(new HandlingRequest(socket, colManager, commandExecutor, logger));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Some problems with input/output...", e);
        }
    }
}
