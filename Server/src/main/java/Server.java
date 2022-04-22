import collection.WorkerColManager;
import commands.Command;
import commands.CommandExecutor;
import transferring.Request;
import transferring.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
    private final CommandExecutor commandExecutor;
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final Logger logger;

    public Server(int port, WorkerColManager colManager, Logger logger) throws IOException {
        this.logger = logger;
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocketChannel.socket().bind(address);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        this.commandExecutor = new CommandExecutor(colManager);
    }

    public void run() {
        try {
            while (true) {
                Response response;
                Request request;
                try {
                    selector.select();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Problem with selector", e);
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        continue;
                    }
                    if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        try {
                            request = receiveData(socketChannel);
                            response = handleRequest(request);
                            sendResponse(response, socketChannel);
                            socketChannel.close();
                        } catch (IOException | ClassNotFoundException e) {
                            logger.log(Level.SEVERE, "Problem with processing request", e);
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Some problems with input/output...", e);
        }
    }

    private Request receiveData(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(16000);
        socketChannel.read(buffer);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
        Request request = (Request) objectInputStream.readObject();
        logger.info("Request \"" + request.getCommandName() + "\" received");
        return request;
    }

    private Response handleRequest(Request request) {
        List<String> resultList = new ArrayList<>();
        Command command = commandExecutor.getCommand(request.getCommandName());
        if (command == null) {
            resultList.add("Such a command doesn't exist");
            return new Response(resultList);
        }
        resultList = command.action(request.getArgument(), request.getWorker());
        logger.info("Response handled");
        return new Response(resultList);
    }

    private void sendResponse(Response response, SocketChannel socketChannel) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        objectOutputStream.flush();
        socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        logger.info("Response sent");
    }
}
