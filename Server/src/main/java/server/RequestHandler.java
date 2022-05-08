package server;

import collection.WorkerColManager;
import commands.CommandExecutor;
import transferring.Request;

import java.net.Socket;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class RequestHandler {
    private static final ExecutorService poolHandler = Executors.newCachedThreadPool();

    public static void handleRequest(Request request, Socket socket,
                                     WorkerColManager colManager, CommandExecutor executor, Logger logger) {
        RequestProcessingTask task = new RequestProcessingTask(request, socket, colManager, executor, logger);
        poolHandler.execute(task);
    }
}
