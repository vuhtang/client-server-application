package server;

import collection.WorkerColManager;
import commands.Command;
import commands.CommandExecutor;
import exceptions.InvalidInputException;
import repository.SqlManager;
import transferring.Request;
import transferring.Response;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class RequestHandler {
    private static final ExecutorService poolHandler = Executors.newCachedThreadPool();

    public static void handleRequest(Request request, Socket socket,
                                     WorkerColManager colManager, CommandExecutor executor, Logger logger) {
        poolHandler.execute(() -> {
            List<String> resultList = new ArrayList<>();
            SqlManager sqlManager = colManager.getSqlManager();
            Command command = executor.getCommand(request.getCommandName());
            try {
                if (!sqlManager.checkUser(request.getToken()) && !command.getName().equals("register")) {
                    resultList.add("Such user wasn't registered");
                    ResponseSender.sendResponse(new Response(resultList), socket, logger);
                    return;
                }
            } catch (SQLException | InvalidInputException e) {
                resultList.add(e.getMessage());
                ResponseSender.sendResponse(new Response(resultList), socket, logger);
                return;
            }
            if (command == null) {
                resultList.add("Such a command doesn't exist");
                ResponseSender.sendResponse(new Response(resultList), socket, logger);
                return;
            }
            resultList = command.action(request.getArgument(), request.getWorker(), request.getToken());
            logger.info("Response handled");
            ResponseSender.sendResponse(new Response(resultList), socket, logger);
        });
    }
}
