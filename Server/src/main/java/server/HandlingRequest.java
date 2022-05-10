package server;

import collection.WorkerColManager;
import commands.CommandExecutor;
import transferring.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandlingRequest implements Runnable{
    private final Socket socket;
    private final Logger logger;
    private final WorkerColManager colManager;
    private final CommandExecutor executor;

    public HandlingRequest(Socket socket, WorkerColManager colManager,
                           CommandExecutor executor, Logger logger) {
        this.socket = socket;
        this.logger = logger;
        this.colManager = colManager;
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Request request = (Request) objectInputStream.readObject();
            logger.info("Request \"" + request.getCommandName() + "\" received");
            RequestHandler.handleRequest(request, socket, colManager, executor, logger);
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Problem with receiving: ", e);
        }
    }
}
