package server;

import transferring.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseSender {
    private static final ExecutorService poolSender = Executors.newFixedThreadPool(9);

    public static void sendResponse(Response response, Socket socket, Logger logger) {
        poolSender.execute(() -> {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(response);
                objectOutputStream.flush();
                byteArrayOutputStream.writeTo(socket.getOutputStream());
                logger.info("Response sent");
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "IO exception occurs: ", e);
            }
        });
    }
}
